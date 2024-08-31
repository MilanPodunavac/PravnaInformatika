import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOptuznica } from 'app/shared/model/optuznica.model';
import { getEntities } from './optuznica.reducer';

export const Optuznica = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const optuznicaList = useAppSelector(state => state.optuznica.entities);
  const loading = useAppSelector(state => state.optuznica.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="optuznica-heading" data-cy="OptuznicaHeading">
        <Translate contentKey="pravnaInformatikaApp.optuznica.home.title">Optuznicas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.optuznica.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/optuznica/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.optuznica.home.createLabel">Create new Optuznica</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {optuznicaList && optuznicaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.optuznica.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.optuznica.kod">Kod</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.optuznica.datum">Datum</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.optuznica.ustanova">Ustanova</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {optuznicaList.map((optuznica, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/optuznica/${optuznica.id}`} color="link" size="sm">
                      {optuznica.id}
                    </Button>
                  </td>
                  <td>{optuznica.kod}</td>
                  <td>{optuznica.datum ? <TextFormat type="date" value={optuznica.datum} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{optuznica.ustanova}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/optuznica/${optuznica.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/optuznica/${optuznica.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/optuznica/${optuznica.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="pravnaInformatikaApp.optuznica.home.notFound">No Optuznicas found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Optuznica;
