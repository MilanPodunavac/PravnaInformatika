import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IKazna } from 'app/shared/model/kazna.model';
import { getEntities } from './kazna.reducer';

export const Kazna = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const kaznaList = useAppSelector(state => state.kazna.entities);
  const loading = useAppSelector(state => state.kazna.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="kazna-heading" data-cy="KaznaHeading">
        <Translate contentKey="pravnaInformatikaApp.kazna.home.title">Kaznas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.kazna.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/kazna/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.kazna.home.createLabel">Create new Kazna</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {kaznaList && kaznaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.tip">Tip</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.duzinaPritvora">Duzina Pritvora</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.uracunavanjePritvora">Uracunavanje Pritvora</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.kolicinaNovca">Kolicina Novca</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.primalacNovca">Primalac Novca</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.nazivImovine">Naziv Imovine</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.kazna.presuda">Presuda</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {kaznaList.map((kazna, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/kazna/${kazna.id}`} color="link" size="sm">
                      {kazna.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.TipKazne.${kazna.tip}`} />
                  </td>
                  <td>{kazna.duzinaPritvora}</td>
                  <td>{kazna.uracunavanjePritvora ? 'true' : 'false'}</td>
                  <td>{kazna.kolicinaNovca}</td>
                  <td>{kazna.primalacNovca}</td>
                  <td>{kazna.nazivImovine}</td>
                  <td>{kazna.presuda ? <Link to={`/presuda/${kazna.presuda.id}`}>{kazna.presuda.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/kazna/${kazna.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/kazna/${kazna.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/kazna/${kazna.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="pravnaInformatikaApp.kazna.home.notFound">No Kaznas found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Kazna;
