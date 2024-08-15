import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { getEntities } from './optuzeni.reducer';

export const Optuzeni = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const optuzeniList = useAppSelector(state => state.optuzeni.entities);
  const loading = useAppSelector(state => state.optuzeni.loading);
  const totalItems = useAppSelector(state => state.optuzeni.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="optuzeni-heading" data-cy="OptuzeniHeading">
        <Translate contentKey="pravnaInformatikaApp.optuzeni.home.title">Optuzenis</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.optuzeni.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/optuzeni/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.optuzeni.home.createLabel">Create new Optuzeni</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {optuzeniList && optuzeniList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ime')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.ime">Ime</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('jmbg')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.jmbg">Jmbg</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('imeOca')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.imeOca">Ime Oca</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('imeMajke')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.imeMajke">Ime Majke</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('pol')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.pol">Pol</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('datumRodjenja')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.datumRodjenja">Datum Rodjenja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mestoRodjenja')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.mestoRodjenja">Mesto Rodjenja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('drzavaRodjenja')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.drzavaRodjenja">Drzava Rodjenja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('prebivaliste')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.prebivaliste">Prebivaliste</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bracniStatus')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.bracniStatus">Bracni Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('brojDece')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.brojDece">Broj Dece</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('brojMaloletneDece')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.brojMaloletneDece">Broj Maloletne Dece</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('imovinskoStanje')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.imovinskoStanje">Imovinsko Stanje</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('obrazovanje')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.obrazovanje">Obrazovanje</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('zaposlenje')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.zaposlenje">Zaposlenje</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mestoZaposlenja')}>
                  <Translate contentKey="pravnaInformatikaApp.optuzeni.mestoZaposlenja">Mesto Zaposlenja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {optuzeniList.map((optuzeni, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/optuzeni/${optuzeni.id}`} color="link" size="sm">
                      {optuzeni.id}
                    </Button>
                  </td>
                  <td>{optuzeni.ime}</td>
                  <td>{optuzeni.jmbg}</td>
                  <td>{optuzeni.imeOca}</td>
                  <td>{optuzeni.imeMajke}</td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.Pol.${optuzeni.pol}`} />
                  </td>
                  <td>
                    {optuzeni.datumRodjenja ? (
                      <TextFormat type="date" value={optuzeni.datumRodjenja} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{optuzeni.mestoRodjenja}</td>
                  <td>{optuzeni.drzavaRodjenja}</td>
                  <td>{optuzeni.prebivaliste}</td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.BracniStatus.${optuzeni.bracniStatus}`} />
                  </td>
                  <td>{optuzeni.brojDece}</td>
                  <td>{optuzeni.brojMaloletneDece}</td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.ImovinskoStanje.${optuzeni.imovinskoStanje}`} />
                  </td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.TipObrazovanja.${optuzeni.obrazovanje}`} />
                  </td>
                  <td>{optuzeni.zaposlenje}</td>
                  <td>{optuzeni.mestoZaposlenja}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/optuzeni/${optuzeni.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/optuzeni/${optuzeni.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/optuzeni/${optuzeni.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.home.notFound">No Optuzenis found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={optuzeniList && optuzeniList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Optuzeni;
