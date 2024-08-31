import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './optuznica.reducer';

export const OptuznicaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const optuznicaEntity = useAppSelector(state => state.optuznica.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="optuznicaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.optuznica.detail.title">Optuznica</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{optuznicaEntity.id}</dd>
          <dt>
            <span id="kod">
              <Translate contentKey="pravnaInformatikaApp.optuznica.kod">Kod</Translate>
            </span>
          </dt>
          <dd>{optuznicaEntity.kod}</dd>
          <dt>
            <span id="datum">
              <Translate contentKey="pravnaInformatikaApp.optuznica.datum">Datum</Translate>
            </span>
          </dt>
          <dd>{optuznicaEntity.datum ? <TextFormat value={optuznicaEntity.datum} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="ustanova">
              <Translate contentKey="pravnaInformatikaApp.optuznica.ustanova">Ustanova</Translate>
            </span>
          </dt>
          <dd>{optuznicaEntity.ustanova}</dd>
        </dl>
        <Button tag={Link} to="/optuznica" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/optuznica/${optuznicaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OptuznicaDetail;
