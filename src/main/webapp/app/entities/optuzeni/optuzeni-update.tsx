import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { Pol } from 'app/shared/model/enumerations/pol.model';
import { BracniStatus } from 'app/shared/model/enumerations/bracni-status.model';
import { ImovinskoStanje } from 'app/shared/model/enumerations/imovinsko-stanje.model';
import { TipObrazovanja } from 'app/shared/model/enumerations/tip-obrazovanja.model';
import { getEntity, updateEntity, createEntity, reset } from './optuzeni.reducer';

export const OptuzeniUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const optuzeniEntity = useAppSelector(state => state.optuzeni.entity);
  const loading = useAppSelector(state => state.optuzeni.loading);
  const updating = useAppSelector(state => state.optuzeni.updating);
  const updateSuccess = useAppSelector(state => state.optuzeni.updateSuccess);
  const polValues = Object.keys(Pol);
  const bracniStatusValues = Object.keys(BracniStatus);
  const imovinskoStanjeValues = Object.keys(ImovinskoStanje);
  const tipObrazovanjaValues = Object.keys(TipObrazovanja);

  const handleClose = () => {
    navigate('/optuzeni' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...optuzeniEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          pol: 'MUSKI',
          bracniStatus: 'VAN_BRAKA',
          imovinskoStanje: 'LOSE',
          obrazovanje: 'NEOBRAZOVAN',
          ...optuzeniEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.optuzeni.home.createOrEditLabel" data-cy="OptuzeniCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.optuzeni.home.createOrEditLabel">Create or edit a Optuzeni</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="optuzeni-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.ime')}
                id="optuzeni-ime"
                name="ime"
                data-cy="ime"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.jmbg')}
                id="optuzeni-jmbg"
                name="jmbg"
                data-cy="jmbg"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: { value: /^[0-9]{13}/, message: translate('entity.validation.pattern', { pattern: '^[0-9]{13}' }) },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.imeOca')}
                id="optuzeni-imeOca"
                name="imeOca"
                data-cy="imeOca"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.imeMajke')}
                id="optuzeni-imeMajke"
                name="imeMajke"
                data-cy="imeMajke"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.pol')}
                id="optuzeni-pol"
                name="pol"
                data-cy="pol"
                type="select"
              >
                {polValues.map(pol => (
                  <option value={pol} key={pol}>
                    {translate('pravnaInformatikaApp.Pol.' + pol)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.datumRodjenja')}
                id="optuzeni-datumRodjenja"
                name="datumRodjenja"
                data-cy="datumRodjenja"
                type="date"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.mestoRodjenja')}
                id="optuzeni-mestoRodjenja"
                name="mestoRodjenja"
                data-cy="mestoRodjenja"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.drzavaRodjenja')}
                id="optuzeni-drzavaRodjenja"
                name="drzavaRodjenja"
                data-cy="drzavaRodjenja"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.prebivaliste')}
                id="optuzeni-prebivaliste"
                name="prebivaliste"
                data-cy="prebivaliste"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.bracniStatus')}
                id="optuzeni-bracniStatus"
                name="bracniStatus"
                data-cy="bracniStatus"
                type="select"
              >
                {bracniStatusValues.map(bracniStatus => (
                  <option value={bracniStatus} key={bracniStatus}>
                    {translate('pravnaInformatikaApp.BracniStatus.' + bracniStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.brojDece')}
                id="optuzeni-brojDece"
                name="brojDece"
                data-cy="brojDece"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.brojMaloletneDece')}
                id="optuzeni-brojMaloletneDece"
                name="brojMaloletneDece"
                data-cy="brojMaloletneDece"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.imovinskoStanje')}
                id="optuzeni-imovinskoStanje"
                name="imovinskoStanje"
                data-cy="imovinskoStanje"
                type="select"
              >
                {imovinskoStanjeValues.map(imovinskoStanje => (
                  <option value={imovinskoStanje} key={imovinskoStanje}>
                    {translate('pravnaInformatikaApp.ImovinskoStanje.' + imovinskoStanje)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.obrazovanje')}
                id="optuzeni-obrazovanje"
                name="obrazovanje"
                data-cy="obrazovanje"
                type="select"
              >
                {tipObrazovanjaValues.map(tipObrazovanja => (
                  <option value={tipObrazovanja} key={tipObrazovanja}>
                    {translate('pravnaInformatikaApp.TipObrazovanja.' + tipObrazovanja)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.zaposlenje')}
                id="optuzeni-zaposlenje"
                name="zaposlenje"
                data-cy="zaposlenje"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.optuzeni.mestoZaposlenja')}
                id="optuzeni-mestoZaposlenja"
                name="mestoZaposlenja"
                data-cy="mestoZaposlenja"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/optuzeni" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OptuzeniUpdate;
