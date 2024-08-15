import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './clan-zakona.reducer';

export const ClanZakonaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const clanZakonaEntity = useAppSelector(state => state.clanZakona.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="clanZakonaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.clanZakona.detail.title">ClanZakona</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{clanZakonaEntity.id}</dd>
          <dt>
            <span id="broj">
              <Translate contentKey="pravnaInformatikaApp.clanZakona.broj">Broj</Translate>
            </span>
          </dt>
          <dd>{clanZakonaEntity.broj}</dd>
          <dt>
            <span id="glava">
              <Translate contentKey="pravnaInformatikaApp.clanZakona.glava">Glava</Translate>
            </span>
          </dt>
          <dd>{clanZakonaEntity.glava}</dd>
          <dt>
            <span id="naziv">
              <Translate contentKey="pravnaInformatikaApp.clanZakona.naziv">Naziv</Translate>
            </span>
          </dt>
          <dd>{clanZakonaEntity.naziv}</dd>
          <dt>
            <span id="tekst">
              <Translate contentKey="pravnaInformatikaApp.clanZakona.tekst">Tekst</Translate>
            </span>
          </dt>
          <dd>{clanZakonaEntity.tekst}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.clanZakona.zakon">Zakon</Translate>
          </dt>
          <dd>{clanZakonaEntity.zakon ? clanZakonaEntity.zakon.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/clan-zakona" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/clan-zakona/${clanZakonaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClanZakonaDetail;
