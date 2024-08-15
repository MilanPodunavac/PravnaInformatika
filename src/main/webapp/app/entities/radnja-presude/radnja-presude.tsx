import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { getEntities } from './radnja-presude.reducer';

export const RadnjaPresude = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const radnjaPresudeList = useAppSelector(state => state.radnjaPresude.entities);
  const loading = useAppSelector(state => state.radnjaPresude.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="radnja-presude-heading" data-cy="RadnjaPresudeHeading">
        <Translate contentKey="pravnaInformatikaApp.radnjaPresude.home.title">Radnja Presudes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="pravnaInformatikaApp.radnjaPresude.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/radnja-presude/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="pravnaInformatikaApp.radnjaPresude.home.createLabel">Create new Radnja Presude</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {radnjaPresudeList && radnjaPresudeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.vremeRadnje">Vreme Radnje</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.mestoRadnje">Mesto Radnje</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.bitneNapomene">Bitne Napomene</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.mestoSmrti">Mesto Smrti</Translate>
                </th>
                <th>
                  <Translate contentKey="pravnaInformatikaApp.radnjaPresude.vremeSmrti">Vreme Smrti</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {radnjaPresudeList.map((radnjaPresude, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/radnja-presude/${radnjaPresude.id}`} color="link" size="sm">
                      {radnjaPresude.id}
                    </Button>
                  </td>
                  <td>
                    {radnjaPresude.vremeRadnje ? (
                      <TextFormat type="date" value={radnjaPresude.vremeRadnje} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{radnjaPresude.mestoRadnje}</td>
                  <td>{radnjaPresude.bitneNapomene}</td>
                  <td>{radnjaPresude.mestoSmrti}</td>
                  <td>
                    {radnjaPresude.vremeSmrti ? (
                      <TextFormat type="date" value={radnjaPresude.vremeSmrti} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/radnja-presude/${radnjaPresude.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/radnja-presude/${radnjaPresude.id}/edit`}
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
                        to={`/radnja-presude/${radnjaPresude.id}/delete`}
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
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.home.notFound">No Radnja Presudes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default RadnjaPresude;
