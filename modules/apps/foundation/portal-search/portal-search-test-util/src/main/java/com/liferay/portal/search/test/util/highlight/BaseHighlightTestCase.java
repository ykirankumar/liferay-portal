/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.test.util.highlight;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public abstract class BaseHighlightTestCase extends BaseIndexingTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		LocalizationUtil localizationUtil = new LocalizationUtil();

		localizationUtil.setLocalization(createLocalization());
	}

	@Test
	public void testEllipsis() throws Exception {
		addDocuments(
			Field.TITLE,
			Arrays.asList(
				"alpha", "alpha beta", "alpha beta alpha",
				"alpha beta gamma alpha eta theta alpha zeta eta alpha iota",
				"alpha beta gamma delta epsilon zeta eta theta iota alpha"));

		Query query = new StringQuery(Field.TITLE.concat(":alpha"));

		assertSearch(
			Field.TITLE, query,
			queryConfig -> queryConfig.setHighlightFragmentSize(20),
			toFullHighlights(
				"[H]alpha[/H]", "[H]alpha[/H] beta",
				"[H]alpha[/H] beta [H]alpha[/H]",
				"[H]alpha[/H] beta gamma...[H]alpha[/H] eta theta [H]alpha" +
					"[/H]...zeta eta [H]alpha[/H] iota",
				"[H]alpha[/H] beta gamma...theta iota [H]alpha[/H]"));
	}

	protected void addDocuments(
			String fieldName, Collection<String> fieldValues)
		throws Exception {

		for (String fieldValue : fieldValues) {
			addDocument(
				DocumentCreationHelpers.singleText(fieldName, fieldValue));
		}
	}

	protected void assertSearch(
			String fieldName, Query query, Consumer<QueryConfig> consumer,
			List<String> expectedValues)
		throws Exception {

		SearchContext searchContext = createSearchContext();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.addHighlightFieldNames(fieldName);
		queryConfig.addSelectedFieldNames(fieldName);
		queryConfig.setHighlightEnabled(true);
		queryConfig.setHighlightRequireFieldMatch(true);

		consumer.accept(queryConfig);

		query.setQueryConfig(queryConfig);

		IdempotentRetryAssert.retryAssert(
			5, TimeUnit.SECONDS,
			() -> {
				Hits hits = search(searchContext, query);

				String snippetFieldName = "snippet_".concat(fieldName);

				DocumentsAssert.assertValuesIgnoreRelevance(
					(String)searchContext.getAttribute("queryString"),
					hits.getDocs(), snippetFieldName, expectedValues);

				return null;
			});
	}

	protected Localization createLocalization() {
		Localization localization = Mockito.mock(Localization.class);

		Mockito.doReturn(
			StringPool.BLANK
		).when(
			localization
		).getLocalizedName(
			Mockito.anyString(), Mockito.anyString()
		);

		return localization;
	}

	protected String toFullHighlight(String s) {
		return StringUtil.replace(
			s, new String[] {"[H]", "[/H]"},
			new String[] {
				HighlightUtil.HIGHLIGHT_TAG_OPEN,
				HighlightUtil.HIGHLIGHT_TAG_CLOSE
			});
	}

	protected List<String> toFullHighlights(String... strings) {
		Stream<String> stream = Stream.of(strings);

		return stream.map(
			this::toFullHighlight
		).collect(
			Collectors.toList()
		);
	}

}