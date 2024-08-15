import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { getEntities as getRadnjaPresudes } from 'app/entities/radnja-presude/radnja-presude.reducer';
import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { getEntities as getOptuzenis } from 'app/entities/optuzeni/optuzeni.reducer';
import { IOsoba } from 'app/shared/model/osoba.model';
import { getEntities as getOsobas } from 'app/entities/osoba/osoba.reducer';
import { IPresuda } from 'app/shared/model/presuda.model';
import { TipPresude } from 'app/shared/model/enumerations/tip-presude.model';
import { TipUbistva } from 'app/shared/model/enumerations/tip-ubistva.model';
import { getEntity, updateEntity, createEntity, reset } from './presuda.reducer';

export const PresudaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const radnjaPresudes = useAppSelector(state => state.radnjaPresude.entities);
  const optuzenis = useAppSelector(state => state.optuzeni.entities);
  const osobas = useAppSelector(state => state.osoba.entities);
  const presudaEntity = useAppSelector(state => state.presuda.entity);
  const loading = useAppSelector(state => state.presuda.loading);
  const updating = useAppSelector(state => state.presuda.updating);
  const updateSuccess = useAppSelector(state => state.presuda.updateSuccess);
  const tipPresudeValues = Object.keys(TipPresude);
  const tipUbistvaValues = Object.keys(TipUbistva);

  const handleClose = () => {
    navigate('/presuda');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getRadnjaPresudes({}));
    dispatch(getOptuzenis({}));
    dispatch(getOsobas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...presudaEntity,
      ...values,
      radnja: radnjaPresudes.find(it => it.id.toString() === values.radnja.toString()),
      optuzeni: optuzenis.find(it => it.id.toString() === values.optuzeni.toString()),
      sudija: osobas.find(it => it.id.toString() === values.sudija.toString()),
      zapisnicar: osobas.find(it => it.id.toString() === values.zapisnicar.toString()),
      tuzilac: osobas.find(it => it.id.toString() === values.tuzilac.toString()),
      branilac: osobas.find(it => it.id.toString() === values.branilac.toString()),
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
          tip: 'PRVOSTEPENI_KRIVICNI_PREDMET',
          nacin: 'SA_PREDUMISLJANJEM',
          ...presudaEntity,
          radnja: presudaEntity?.radnja?.id,
          optuzeni: presudaEntity?.optuzeni?.id,
          sudija: presudaEntity?.sudija?.id,
          zapisnicar: presudaEntity?.zapisnicar?.id,
          tuzilac: presudaEntity?.tuzilac?.id,
          branilac: presudaEntity?.branilac?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.presuda.home.createOrEditLabel" data-cy="PresudaCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.presuda.home.createOrEditLabel">Create or edit a Presuda</Translate>
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
                  id="presuda-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.tekst')}
                id="presuda-tekst"
                name="tekst"
                data-cy="tekst"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.datum')}
                id="presuda-datum"
                name="datum"
                data-cy="datum"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.datumObjave')}
                id="presuda-datumObjave"
                name="datumObjave"
                data-cy="datumObjave"
                type="date"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.datumPritvora')}
                id="presuda-datumPritvora"
                name="datumPritvora"
                data-cy="datumPritvora"
                type="date"
              />
              <ValidatedField label={translate('pravnaInformatikaApp.presuda.tip')} id="presuda-tip" name="tip" data-cy="tip" type="select">
                {tipPresudeValues.map(tipPresude => (
                  <option value={tipPresude} key={tipPresude}>
                    {translate('pravnaInformatikaApp.TipPresude.' + tipPresude)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.broj')}
                id="presuda-broj"
                name="broj"
                data-cy="broj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.godina')}
                id="presuda-godina"
                name="godina"
                data-cy="godina"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.optuznica')}
                id="presuda-optuznica"
                name="optuznica"
                data-cy="optuznica"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.datumOptuznice')}
                id="presuda-datumOptuznice"
                name="datumOptuznice"
                data-cy="datumOptuznice"
                type="date"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.pokusaj')}
                id="presuda-pokusaj"
                name="pokusaj"
                data-cy="pokusaj"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.krivica')}
                id="presuda-krivica"
                name="krivica"
                data-cy="krivica"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.nacin')}
                id="presuda-nacin"
                name="nacin"
                data-cy="nacin"
                type="select"
              >
                {tipUbistvaValues.map(tipUbistva => (
                  <option value={tipUbistva} key={tipUbistva}>
                    {translate('pravnaInformatikaApp.TipUbistva.' + tipUbistva)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="presuda-radnja"
                name="radnja"
                data-cy="radnja"
                label={translate('pravnaInformatikaApp.presuda.radnja')}
                type="select"
                required
              >
                <option value="" key="0" />
                {radnjaPresudes
                  ? radnjaPresudes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="presuda-optuzeni"
                name="optuzeni"
                data-cy="optuzeni"
                label={translate('pravnaInformatikaApp.presuda.optuzeni')}
                type="select"
                required
              >
                <option value="" key="0" />
                {optuzenis
                  ? optuzenis.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="presuda-sudija"
                name="sudija"
                data-cy="sudija"
                label={translate('pravnaInformatikaApp.presuda.sudija')}
                type="select"
                required
              >
                <option value="" key="0" />
                {osobas
                  ? osobas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="presuda-zapisnicar"
                name="zapisnicar"
                data-cy="zapisnicar"
                label={translate('pravnaInformatikaApp.presuda.zapisnicar')}
                type="select"
                required
              >
                <option value="" key="0" />
                {osobas
                  ? osobas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="presuda-tuzilac"
                name="tuzilac"
                data-cy="tuzilac"
                label={translate('pravnaInformatikaApp.presuda.tuzilac')}
                type="select"
                required
              >
                <option value="" key="0" />
                {osobas
                  ? osobas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="presuda-branilac"
                name="branilac"
                data-cy="branilac"
                label={translate('pravnaInformatikaApp.presuda.branilac')}
                type="select"
                required
              >
                <option value="" key="0" />
                {osobas
                  ? osobas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/presuda" replace color="info">
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

export default PresudaUpdate;
