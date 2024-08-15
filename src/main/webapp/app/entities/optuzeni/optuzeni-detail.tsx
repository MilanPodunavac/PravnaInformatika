import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './optuzeni.reducer';

export const OptuzeniDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const optuzeniEntity = useAppSelector(state => state.optuzeni.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="optuzeniDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.optuzeni.detail.title">Optuzeni</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.id}</dd>
          <dt>
            <span id="ime">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.ime">Ime</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.ime}</dd>
          <dt>
            <span id="jmbg">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.jmbg">Jmbg</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.jmbg}</dd>
          <dt>
            <span id="imeOca">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.imeOca">Ime Oca</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.imeOca}</dd>
          <dt>
            <span id="imeMajke">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.imeMajke">Ime Majke</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.imeMajke}</dd>
          <dt>
            <span id="pol">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.pol">Pol</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.pol}</dd>
          <dt>
            <span id="datumRodjenja">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.datumRodjenja">Datum Rodjenja</Translate>
            </span>
          </dt>
          <dd>
            {optuzeniEntity.datumRodjenja ? (
              <TextFormat value={optuzeniEntity.datumRodjenja} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="mestoRodjenja">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.mestoRodjenja">Mesto Rodjenja</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.mestoRodjenja}</dd>
          <dt>
            <span id="drzavaRodjenja">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.drzavaRodjenja">Drzava Rodjenja</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.drzavaRodjenja}</dd>
          <dt>
            <span id="prebivaliste">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.prebivaliste">Prebivaliste</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.prebivaliste}</dd>
          <dt>
            <span id="bracniStatus">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.bracniStatus">Bracni Status</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.bracniStatus}</dd>
          <dt>
            <span id="brojDece">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.brojDece">Broj Dece</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.brojDece}</dd>
          <dt>
            <span id="brojMaloletneDece">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.brojMaloletneDece">Broj Maloletne Dece</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.brojMaloletneDece}</dd>
          <dt>
            <span id="imovinskoStanje">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.imovinskoStanje">Imovinsko Stanje</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.imovinskoStanje}</dd>
          <dt>
            <span id="obrazovanje">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.obrazovanje">Obrazovanje</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.obrazovanje}</dd>
          <dt>
            <span id="zaposlenje">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.zaposlenje">Zaposlenje</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.zaposlenje}</dd>
          <dt>
            <span id="mestoZaposlenja">
              <Translate contentKey="pravnaInformatikaApp.optuzeni.mestoZaposlenja">Mesto Zaposlenja</Translate>
            </span>
          </dt>
          <dd>{optuzeniEntity.mestoZaposlenja}</dd>
        </dl>
        <Button tag={Link} to="/optuzeni" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/optuzeni/${optuzeniEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OptuzeniDetail;
