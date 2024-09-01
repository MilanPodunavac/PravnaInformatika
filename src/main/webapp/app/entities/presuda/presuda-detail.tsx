import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './presuda.reducer';

export const PresudaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const presudaEntity = useAppSelector(state => state.presuda.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="presudaDetailsHeading">
          <Translate contentKey="pravnaInformatikaApp.presuda.detail.title">Presuda</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.id}</dd>
          <dt>
            <span id="datum">
              <Translate contentKey="pravnaInformatikaApp.presuda.datum">Datum</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.datum ? <TextFormat value={presudaEntity.datum} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="datumPritvora">
              <Translate contentKey="pravnaInformatikaApp.presuda.datumPritvora">Datum Pritvora</Translate>
            </span>
          </dt>
          <dd>
            {presudaEntity.datumPritvora ? (
              <TextFormat value={presudaEntity.datumPritvora} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="kod">
              <Translate contentKey="pravnaInformatikaApp.presuda.kod">Kod</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.kod}</dd>
          <dt>
            <span id="tip">
              <Translate contentKey="pravnaInformatikaApp.presuda.tip">Tip</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.tip}</dd>
          <dt>
            <span id="broj">
              <Translate contentKey="pravnaInformatikaApp.presuda.broj">Broj</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.broj}</dd>
          <dt>
            <span id="godina">
              <Translate contentKey="pravnaInformatikaApp.presuda.godina">Godina</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.godina}</dd>
          <dt>
            <span id="pokusaj">
              <Translate contentKey="pravnaInformatikaApp.presuda.pokusaj">Pokusaj</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.pokusaj ? 'true' : 'false'}</dd>
          <dt>
            <span id="krivica">
              <Translate contentKey="pravnaInformatikaApp.presuda.krivica">Krivica</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.krivica ? 'true' : 'false'}</dd>
          <dt>
            <span id="nacin">
              <Translate contentKey="pravnaInformatikaApp.presuda.nacin">Nacin</Translate>
            </span>
          </dt>
          <dd>{presudaEntity.nacin}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.radnja">Radnja</Translate>
          </dt>
          <dd>{presudaEntity.radnja ? presudaEntity.radnja.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.optuznica">Optuznica</Translate>
          </dt>
          <dd>{presudaEntity.optuznica ? presudaEntity.optuznica.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.vece">Vece</Translate>
          </dt>
          <dd>
            {presudaEntity.veces
              ? presudaEntity.veces.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {presudaEntity.veces && i === presudaEntity.veces.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.clanoviZakona">Clanovi Zakona</Translate>
          </dt>
          <dd>
            {presudaEntity.clanoviZakonas
              ? presudaEntity.clanoviZakonas.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {presudaEntity.clanoviZakonas && i === presudaEntity.clanoviZakonas.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.optuzeni">Optuzeni</Translate>
          </dt>
          <dd>{presudaEntity.optuzeni ? presudaEntity.optuzeni.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.sudija">Sudija</Translate>
          </dt>
          <dd>{presudaEntity.sudija ? presudaEntity.sudija.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.zapisnicar">Zapisnicar</Translate>
          </dt>
          <dd>{presudaEntity.zapisnicar ? presudaEntity.zapisnicar.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.tuzilac">Tuzilac</Translate>
          </dt>
          <dd>{presudaEntity.tuzilac ? presudaEntity.tuzilac.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.branilac">Branilac</Translate>
          </dt>
          <dd>{presudaEntity.branilac ? presudaEntity.branilac.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.osteceni">Osteceni</Translate>
          </dt>
          <dd>{presudaEntity.osteceni ? presudaEntity.osteceni.id : ''}</dd>
          <dt>
            <Translate contentKey="pravnaInformatikaApp.presuda.sud">Sud</Translate>
          </dt>
          <dd>{presudaEntity.sud ? presudaEntity.sud.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/presuda" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/presuda/${presudaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PresudaDetail;
