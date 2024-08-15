import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPovreda } from 'app/shared/model/povreda.model';
import { getEntities } from './povreda.reducer';

export const Povreda = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const povredaList = useAppSelector(state => state.povreda.entities);
  const loading = useAppSelector(state => state.povreda.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="povreda-heading" data-cy="PovredaHeading">
        <Translate contentKey="pravnaInformatikaApp.povreda.home.title">Povredas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.povreda.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/povreda/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.povreda.home.createLabel">Create new Povreda</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {povredaList && povredaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.povreda.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.povreda.oruzje">Oruzje</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.povreda.deoTela">Deo Tela</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.povreda.povrede">Povrede</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.povreda.radnja">Radnja</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {povredaList.map((povreda, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/povreda/${povreda.id}`} color="link" size="sm">
                      {povreda.id}
                    </Button>
                  </td>
                  <td>{povreda.oruzje}</td>
                  <td>{povreda.deoTela}</td>
                  <td>{povreda.povrede}</td>
                  <td>{povreda.radnja ? <Link to={`/radnja-presude/${povreda.radnja.id}`}>{povreda.radnja.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/povreda/${povreda.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/povreda/${povreda.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/povreda/${povreda.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="pravnaInformatikaApp.povreda.home.notFound">No Povredas found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Povreda;
