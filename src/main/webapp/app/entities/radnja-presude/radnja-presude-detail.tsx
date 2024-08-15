import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './radnja-presude.reducer';

export const RadnjaPresudeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const radnjaPresudeEntity = useAppSelector(state => state.radnjaPresude.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="radnjaPresudeDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.radnjaPresude.detail.title">RadnjaPresude</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{radnjaPresudeEntity.id}</dd>
          <dt>
            <span id="vremeRadnje">
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.vremeRadnje">Vreme Radnje</Translate>
            </span>
          </dt>
          <dd>
            {radnjaPresudeEntity.vremeRadnje ? (
              <TextFormat value={radnjaPresudeEntity.vremeRadnje} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="mestoRadnje">
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.mestoRadnje">Mesto Radnje</Translate>
            </span>
          </dt>
          <dd>{radnjaPresudeEntity.mestoRadnje}</dd>
          <dt>
            <span id="bitneNapomene">
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.bitneNapomene">Bitne Napomene</Translate>
            </span>
          </dt>
          <dd>{radnjaPresudeEntity.bitneNapomene}</dd>
          <dt>
            <span id="mestoSmrti">
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.mestoSmrti">Mesto Smrti</Translate>
            </span>
          </dt>
          <dd>{radnjaPresudeEntity.mestoSmrti}</dd>
          <dt>
            <span id="vremeSmrti">
              <Translate contentKey="pravnaInformatikaApp.radnjaPresude.vremeSmrti">Vreme Smrti</Translate>
            </span>
          </dt>
          <dd>
            {radnjaPresudeEntity.vremeSmrti ? (
              <TextFormat value={radnjaPresudeEntity.vremeSmrti} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/radnja-presude" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/radnja-presude/${radnjaPresudeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RadnjaPresudeDetail;
