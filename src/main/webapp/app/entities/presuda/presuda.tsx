import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPresuda } from 'app/shared/model/presuda.model';
import { getEntities, reset } from './presuda.reducer';

export const Presuda = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const presudaList = useAppSelector(state => state.presuda.entities);
  const loading = useAppSelector(state => state.presuda.loading);
  const totalItems = useAppSelector(state => state.presuda.totalItems);
  const links = useAppSelector(state => state.presuda.links);
  const entity = useAppSelector(state => state.presuda.entity);
  const updateSuccess = useAppSelector(state => state.presuda.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  return (
    <div>
      <h2 id="presuda-heading" data-cy="PresudaHeading">
        <Translate contentKey="pravnaInformatikaApp.presuda.home.title">Presudas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.presuda.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/presuda/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.presuda.home.createLabel">Create new Presuda</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={presudaList ? presudaList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {presudaList && presudaList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('datum')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.datum">Datum</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('datumPritvora')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.datumPritvora">Datum Pritvora</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('kod')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.kod">Kod</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('tip')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.tip">Tip</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('broj')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.broj">Broj</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('godina')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.godina">Godina</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('pokusaj')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.pokusaj">Pokusaj</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('krivica')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.krivica">Krivica</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('nacin')}>
                    <Translate contentKey="pravnaInformatikaApp.presuda.nacin">Nacin</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.radnja">Radnja</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.optuznica">Optuznica</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.optuzeni">Optuzeni</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.sudija">Sudija</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.zapisnicar">Zapisnicar</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.tuzilac">Tuzilac</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.branilac">Branilac</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.osteceni">Osteceni</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pravnaInformatikaApp.presuda.sud">Sud</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {presudaList.map((presuda, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/presuda/${presuda.id}`} color="link" size="sm">
                        {presuda.id}
                      </Button>
                    </td>
                    <td>{presuda.datum ? <TextFormat type="date" value={presuda.datum} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                    <td>
                      {presuda.datumPritvora ? (
                        <TextFormat type="date" value={presuda.datumPritvora} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{presuda.kod}</td>
                    <td>
                      <Translate contentKey={`pravnaInformatikaApp.TipPresude.${presuda.tip}`} />
                    </td>
                    <td>{presuda.broj}</td>
                    <td>{presuda.godina}</td>
                    <td>{presuda.pokusaj ? 'true' : 'false'}</td>
                    <td>{presuda.krivica ? 'true' : 'false'}</td>
                    <td>
                      <Translate contentKey={`pravnaInformatikaApp.TipUbistva.${presuda.nacin}`} />
                    </td>
                    <td>{presuda.radnja ? <Link to={`/radnja-presude/${presuda.radnja.id}`}>{presuda.radnja.id}</Link> : ''}</td>
                    <td>{presuda.optuznica ? <Link to={`/optuznica/${presuda.optuznica.id}`}>{presuda.optuznica.id}</Link> : ''}</td>
                    <td>{presuda.optuzeni ? <Link to={`/optuzeni/${presuda.optuzeni.id}`}>{presuda.optuzeni.id}</Link> : ''}</td>
                    <td>{presuda.sudija ? <Link to={`/osoba/${presuda.sudija.id}`}>{presuda.sudija.id}</Link> : ''}</td>
                    <td>{presuda.zapisnicar ? <Link to={`/osoba/${presuda.zapisnicar.id}`}>{presuda.zapisnicar.id}</Link> : ''}</td>
                    <td>{presuda.tuzilac ? <Link to={`/osoba/${presuda.tuzilac.id}`}>{presuda.tuzilac.id}</Link> : ''}</td>
                    <td>{presuda.branilac ? <Link to={`/osoba/${presuda.branilac.id}`}>{presuda.branilac.id}</Link> : ''}</td>
                    <td>{presuda.osteceni ? <Link to={`/osoba/${presuda.osteceni.id}`}>{presuda.osteceni.id}</Link> : ''}</td>
                    <td>{presuda.sud ? <Link to={`/sud/${presuda.sud.id}`}>{presuda.sud.id}</Link> : ''}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/presuda/${presuda.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/presuda/${presuda.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/presuda/${presuda.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
                <Translate contentKey="pravnaInformatikaApp.presuda.home.notFound">No Presudas found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Presuda;
