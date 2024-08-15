import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISud } from 'app/shared/model/sud.model';
import { getEntities } from './sud.reducer';

export const Sud = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const sudList = useAppSelector(state => state.sud.entities);
  const loading = useAppSelector(state => state.sud.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="sud-heading" data-cy="SudHeading">
        <Translate contentKey="pravnaInformatikaApp.sud.home.title">Suds</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.sud.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/sud/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.sud.home.createLabel">Create new Sud</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {sudList && sudList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.sud.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.sud.tip">Tip</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.sud.naselje">Naselje</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sudList.map((sud, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/sud/${sud.id}`} color="link" size="sm">
                      {sud.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`pravnaInformatikaApp.TipSuda.${sud.tip}`} />
                  </td>
                  <td>{sud.naselje}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/sud/${sud.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/sud/${sud.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/sud/${sud.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="pravnaInformatikaApp.sud.home.notFound">No Suds found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Sud;
