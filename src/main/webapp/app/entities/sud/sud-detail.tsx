import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sud.reducer';

export const SudDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const sudEntity = useAppSelector(state => state.sud.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sudDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.sud.detail.title">Sud</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sudEntity.id}</dd>
          <dt>
            <span id="naziv">
              <Translate contentKey="pravnaInformatikaApp.sud.naziv">Naziv</Translate>
            </span>
          </dt>
          <dd>{sudEntity.naziv}</dd>
          <dt>
            <span id="tip">
              <Translate contentKey="pravnaInformatikaApp.sud.tip">Tip</Translate>
            </span>
          </dt>
          <dd>{sudEntity.tip}</dd>
          <dt>
            <span id="mesto">
              <Translate contentKey="pravnaInformatikaApp.sud.mesto">Mesto</Translate>
            </span>
          </dt>
          <dd>{sudEntity.mesto}</dd>
        </dl>
        <Button tag={Link} to="/sud" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sud/${sudEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SudDetail;
