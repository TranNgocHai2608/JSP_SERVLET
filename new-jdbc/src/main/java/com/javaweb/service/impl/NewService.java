package com.javaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.INewDAO;
import com.javaweb.model.NewModel;
import com.javaweb.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDAO newDao;

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setCreatedBy("");
		Long newId = newDao.save(newModel);
		return newDao.findOne(newId);
	}

	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDao.findOne(updateNew.getId());
		oldNew.setCreatedDate(oldNew.getCreatedDate());
		oldNew.setCreatedBy(oldNew.getCreatedBy());
		oldNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		oldNew.setModifiedBy("");
		newDao.update(updateNew);
		return newDao.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newDao.delete(id);
		}
	}

}
