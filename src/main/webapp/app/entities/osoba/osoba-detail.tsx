import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './osoba.reducer';

export const OsobaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const osobaEntity = useAppSelector(state => state.osoba.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="osobaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.osoba.detail.title">Osoba</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{osobaEntity.id}</dd>
          <dt>
            <span id="ime">
              <Translate contentKey="pravnaInformatikaApp.osoba.ime">Ime</Translate>
            </span>
          </dt>
          <dd>{osobaEntity.ime}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.osoba.presudeVece">Presude Vece</Translate>
          </dt>
          <dd>
            {osobaEntity.presudeVeces
              ? osobaEntity.presudeVeces.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.presudeVece}</a>
                    {osobaEntity.presudeVeces && i === osobaEntity.presudeVeces.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/osoba" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/osoba/${osobaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OsobaDetail;
