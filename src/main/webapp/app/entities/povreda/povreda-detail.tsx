import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './povreda.reducer';

export const PovredaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const povredaEntity = useAppSelector(state => state.povreda.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="povredaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.povreda.detail.title">Povreda</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{povredaEntity.id}</dd>
          <dt>
            <span id="oruzje">
              <Translate contentKey="pravnaInformatikaApp.povreda.oruzje">Oruzje</Translate>
            </span>
          </dt>
          <dd>{povredaEntity.oruzje}</dd>
          <dt>
            <span id="deoTela">
              <Translate contentKey="pravnaInformatikaApp.povreda.deoTela">Deo Tela</Translate>
            </span>
          </dt>
          <dd>{povredaEntity.deoTela}</dd>
          <dt>
            <span id="povrede">
              <Translate contentKey="pravnaInformatikaApp.povreda.povrede">Povrede</Translate>
            </span>
          </dt>
          <dd>{povredaEntity.povrede}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.povreda.radnja">Radnja</Translate>
          </dt>
          <dd>{povredaEntity.radnja ? povredaEntity.radnja.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/povreda" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/povreda/${povredaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PovredaDetail;
