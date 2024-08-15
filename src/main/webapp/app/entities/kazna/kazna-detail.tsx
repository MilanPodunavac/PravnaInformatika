import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './kazna.reducer';

export const KaznaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const kaznaEntity = useAppSelector(state => state.kazna.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="kaznaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.kazna.detail.title">Kazna</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.id}</dd>
          <dt>
            <span id="tip">
              <Translate contentKey="pravnaInformatikaApp.kazna.tip">Tip</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.tip}</dd>
          <dt>
            <span id="duzinaPritvora">
              <Translate contentKey="pravnaInformatikaApp.kazna.duzinaPritvora">Duzina Pritvora</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.duzinaPritvora}</dd>
          <dt>
            <span id="uracunavanjePritvora">
              <Translate contentKey="pravnaInformatikaApp.kazna.uracunavanjePritvora">Uracunavanje Pritvora</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.uracunavanjePritvora ? 'true' : 'false'}</dd>
          <dt>
            <span id="kolicinaNovca">
              <Translate contentKey="pravnaInformatikaApp.kazna.kolicinaNovca">Kolicina Novca</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.kolicinaNovca}</dd>
          <dt>
            <span id="primalacNovca">
              <Translate contentKey="pravnaInformatikaApp.kazna.primalacNovca">Primalac Novca</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.primalacNovca}</dd>
          <dt>
            <span id="nazivImovine">
              <Translate contentKey="pravnaInformatikaApp.kazna.nazivImovine">Naziv Imovine</Translate>
            </span>
          </dt>
          <dd>{kaznaEntity.nazivImovine}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.kazna.presuda">Presuda</Translate>
          </dt>
          <dd>{kaznaEntity.presuda ? kaznaEntity.presuda.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/kazna" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/kazna/${kaznaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default KaznaDetail;
