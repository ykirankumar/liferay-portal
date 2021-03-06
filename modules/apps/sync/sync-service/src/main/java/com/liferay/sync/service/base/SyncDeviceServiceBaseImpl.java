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

package com.liferay.sync.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDeviceService;
import com.liferay.sync.service.persistence.SyncDLFileVersionDiffPersistence;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.sync.service.persistence.SyncDLObjectPersistence;
import com.liferay.sync.service.persistence.SyncDevicePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the sync device remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.sync.service.impl.SyncDeviceServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.impl.SyncDeviceServiceImpl
 * @see com.liferay.sync.service.SyncDeviceServiceUtil
 * @generated
 */
public abstract class SyncDeviceServiceBaseImpl extends BaseServiceImpl
	implements SyncDeviceService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.sync.service.SyncDeviceServiceUtil} to access the sync device remote service.
	 */

	/**
	 * Returns the sync device local service.
	 *
	 * @return the sync device local service
	 */
	public com.liferay.sync.service.SyncDeviceLocalService getSyncDeviceLocalService() {
		return syncDeviceLocalService;
	}

	/**
	 * Sets the sync device local service.
	 *
	 * @param syncDeviceLocalService the sync device local service
	 */
	public void setSyncDeviceLocalService(
		com.liferay.sync.service.SyncDeviceLocalService syncDeviceLocalService) {
		this.syncDeviceLocalService = syncDeviceLocalService;
	}

	/**
	 * Returns the sync device remote service.
	 *
	 * @return the sync device remote service
	 */
	public SyncDeviceService getSyncDeviceService() {
		return syncDeviceService;
	}

	/**
	 * Sets the sync device remote service.
	 *
	 * @param syncDeviceService the sync device remote service
	 */
	public void setSyncDeviceService(SyncDeviceService syncDeviceService) {
		this.syncDeviceService = syncDeviceService;
	}

	/**
	 * Returns the sync device persistence.
	 *
	 * @return the sync device persistence
	 */
	public SyncDevicePersistence getSyncDevicePersistence() {
		return syncDevicePersistence;
	}

	/**
	 * Sets the sync device persistence.
	 *
	 * @param syncDevicePersistence the sync device persistence
	 */
	public void setSyncDevicePersistence(
		SyncDevicePersistence syncDevicePersistence) {
		this.syncDevicePersistence = syncDevicePersistence;
	}

	/**
	 * Returns the sync dl file version diff local service.
	 *
	 * @return the sync dl file version diff local service
	 */
	public com.liferay.sync.service.SyncDLFileVersionDiffLocalService getSyncDLFileVersionDiffLocalService() {
		return syncDLFileVersionDiffLocalService;
	}

	/**
	 * Sets the sync dl file version diff local service.
	 *
	 * @param syncDLFileVersionDiffLocalService the sync dl file version diff local service
	 */
	public void setSyncDLFileVersionDiffLocalService(
		com.liferay.sync.service.SyncDLFileVersionDiffLocalService syncDLFileVersionDiffLocalService) {
		this.syncDLFileVersionDiffLocalService = syncDLFileVersionDiffLocalService;
	}

	/**
	 * Returns the sync dl file version diff persistence.
	 *
	 * @return the sync dl file version diff persistence
	 */
	public SyncDLFileVersionDiffPersistence getSyncDLFileVersionDiffPersistence() {
		return syncDLFileVersionDiffPersistence;
	}

	/**
	 * Sets the sync dl file version diff persistence.
	 *
	 * @param syncDLFileVersionDiffPersistence the sync dl file version diff persistence
	 */
	public void setSyncDLFileVersionDiffPersistence(
		SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence) {
		this.syncDLFileVersionDiffPersistence = syncDLFileVersionDiffPersistence;
	}

	/**
	 * Returns the sync dl object local service.
	 *
	 * @return the sync dl object local service
	 */
	public com.liferay.sync.service.SyncDLObjectLocalService getSyncDLObjectLocalService() {
		return syncDLObjectLocalService;
	}

	/**
	 * Sets the sync dl object local service.
	 *
	 * @param syncDLObjectLocalService the sync dl object local service
	 */
	public void setSyncDLObjectLocalService(
		com.liferay.sync.service.SyncDLObjectLocalService syncDLObjectLocalService) {
		this.syncDLObjectLocalService = syncDLObjectLocalService;
	}

	/**
	 * Returns the sync dl object remote service.
	 *
	 * @return the sync dl object remote service
	 */
	public com.liferay.sync.service.SyncDLObjectService getSyncDLObjectService() {
		return syncDLObjectService;
	}

	/**
	 * Sets the sync dl object remote service.
	 *
	 * @param syncDLObjectService the sync dl object remote service
	 */
	public void setSyncDLObjectService(
		com.liferay.sync.service.SyncDLObjectService syncDLObjectService) {
		this.syncDLObjectService = syncDLObjectService;
	}

	/**
	 * Returns the sync dl object persistence.
	 *
	 * @return the sync dl object persistence
	 */
	public SyncDLObjectPersistence getSyncDLObjectPersistence() {
		return syncDLObjectPersistence;
	}

	/**
	 * Sets the sync dl object persistence.
	 *
	 * @param syncDLObjectPersistence the sync dl object persistence
	 */
	public void setSyncDLObjectPersistence(
		SyncDLObjectPersistence syncDLObjectPersistence) {
		this.syncDLObjectPersistence = syncDLObjectPersistence;
	}

	/**
	 * Returns the sync dl object finder.
	 *
	 * @return the sync dl object finder
	 */
	public SyncDLObjectFinder getSyncDLObjectFinder() {
		return syncDLObjectFinder;
	}

	/**
	 * Sets the sync dl object finder.
	 *
	 * @param syncDLObjectFinder the sync dl object finder
	 */
	public void setSyncDLObjectFinder(SyncDLObjectFinder syncDLObjectFinder) {
		this.syncDLObjectFinder = syncDLObjectFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncDeviceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SyncDevice.class;
	}

	protected String getModelClassName() {
		return SyncDevice.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = syncDevicePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.sync.service.SyncDeviceLocalService.class)
	protected com.liferay.sync.service.SyncDeviceLocalService syncDeviceLocalService;
	@BeanReference(type = SyncDeviceService.class)
	protected SyncDeviceService syncDeviceService;
	@BeanReference(type = SyncDevicePersistence.class)
	protected SyncDevicePersistence syncDevicePersistence;
	@BeanReference(type = com.liferay.sync.service.SyncDLFileVersionDiffLocalService.class)
	protected com.liferay.sync.service.SyncDLFileVersionDiffLocalService syncDLFileVersionDiffLocalService;
	@BeanReference(type = SyncDLFileVersionDiffPersistence.class)
	protected SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence;
	@BeanReference(type = com.liferay.sync.service.SyncDLObjectLocalService.class)
	protected com.liferay.sync.service.SyncDLObjectLocalService syncDLObjectLocalService;
	@BeanReference(type = com.liferay.sync.service.SyncDLObjectService.class)
	protected com.liferay.sync.service.SyncDLObjectService syncDLObjectService;
	@BeanReference(type = SyncDLObjectPersistence.class)
	protected SyncDLObjectPersistence syncDLObjectPersistence;
	@BeanReference(type = SyncDLObjectFinder.class)
	protected SyncDLObjectFinder syncDLObjectFinder;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}