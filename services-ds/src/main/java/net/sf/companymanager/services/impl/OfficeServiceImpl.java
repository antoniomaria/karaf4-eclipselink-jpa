/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.companymanager.services.impl;

import org.everit.osgi.transaction.helper.api.TransactionHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import net.sf.companymanager.domain.Office;
import net.sf.companymanager.repository.OfficeRepository;
import net.sf.companymanager.services.OfficeService;
import net.sf.companymanager.services.support.AbstractPersistenceService;

@Component
public class OfficeServiceImpl extends AbstractPersistenceService<Office> implements OfficeService {

	private TransactionHelper transactionHelper;

	/**
	 * Set transaction helper.
	 *
	 * @param transactionHelper helper
	 */
	@Reference
	public final void setTransactionHelper(final TransactionHelper transactionHelper) {
		this.transactionHelper = transactionHelper;
	}

	@Override
	public Office save(Office entity) {
		Office result = transactionHelper.required(() -> {
			Office fetched = null;
			if (entity.isNew()) {
				fetched = repository.save(entity);
			} else {
				fetched = repository.findOne(entity.getId());
				fetched.setName(entity.getName());

			}
			return fetched;
		});
		return result;
	}

	@Reference
	public void setOfficeRepository(final OfficeRepository repository) {
		super.repository = repository;
	}

	@Override
	public Office saveWithinBlueprintTx(Office entity) {
		return repository.save(entity);
	}

}
