package net.sf.companymanager.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.sf.companymanager.domain.Office;
import net.sf.companymanager.qbe.Range;
import net.sf.companymanager.qbe.SearchParameters;
import net.sf.companymanager.repository.OfficeRepository;

@Transactional(value = TxType.SUPPORTS)
public class OfficeRepositoryImpl implements OfficeRepository {

	/**
	 * Logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(OfficeRepositoryImpl.class);

	@PersistenceContext(unitName = "companymanager")
	protected EntityManager em;

	public OfficeRepositoryImpl() {
		logger.debug("OfficeRepositoryImpl instance created");
	}

	@Override
	public long countByExample(Office entity, SearchParameters sp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<Office> findByExample(Office example, List<Range<?, ?>> ranges, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Office> findByExample(Office example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Office> findByExample(Office entity, SearchParameters sp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Office> findOneByExample(Office entity, SearchParameters sp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public <S extends Office> S save(S entity) {
		logger.info("Transaction should be started by Blueprint interceptor");
		if (entity.isNew()) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		return entity;
	}

	@Override
	public void deleteByExample(Office entity, SearchParameters sp) {
		// TODO Auto-generated method stub

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Office> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Office findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Office> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
