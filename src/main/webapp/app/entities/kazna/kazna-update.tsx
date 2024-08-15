import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPresuda } from 'app/shared/model/presuda.model';
import { getEntities as getPresudas } from 'app/entities/presuda/presuda.reducer';
import { IKazna } from 'app/shared/model/kazna.model';
import { TipKazne } from 'app/shared/model/enumerations/tip-kazne.model';
import { getEntity, updateEntity, createEntity, reset } from './kazna.reducer';

export const KaznaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const presudas = useAppSelector(state => state.presuda.entities);
  const kaznaEntity = useAppSelector(state => state.kazna.entity);
  const loading = useAppSelector(state => state.kazna.loading);
  const updating = useAppSelector(state => state.kazna.updating);
  const updateSuccess = useAppSelector(state => state.kazna.updateSuccess);
  const tipKazneValues = Object.keys(TipKazne);

  const handleClose = () => {
    navigate('/kazna');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getPresudas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...kaznaEntity,
      ...values,
      presuda: presudas.find(it => it.id.toString() === values.presuda.toString()),
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
          tip: 'ZATVORSKA_KAZNA',
          ...kaznaEntity,
          presuda: kaznaEntity?.presuda?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.kazna.home.createOrEditLabel" data-cy="KaznaCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.kazna.home.createOrEditLabel">Create or edit a Kazna</Translate>
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
                  id="kazna-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('pravnaInformatikaApp.kazna.tip')} id="kazna-tip" name="tip" data-cy="tip" type="select">
                {tipKazneValues.map(tipKazne => (
                  <option value={tipKazne} key={tipKazne}>
                    {translate('pravnaInformatikaApp.TipKazne.' + tipKazne)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('pravnaInformatikaApp.kazna.duzinaPritvora')}
                id="kazna-duzinaPritvora"
                name="duzinaPritvora"
                data-cy="duzinaPritvora"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.kazna.uracunavanjePritvora')}
                id="kazna-uracunavanjePritvora"
                name="uracunavanjePritvora"
                data-cy="uracunavanjePritvora"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.kazna.kolicinaNovca')}
                id="kazna-kolicinaNovca"
                name="kolicinaNovca"
                data-cy="kolicinaNovca"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.kazna.primalacNovca')}
                id="kazna-primalacNovca"
                name="primalacNovca"
                data-cy="primalacNovca"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.kazna.nazivImovine')}
                id="kazna-nazivImovine"
                name="nazivImovine"
                data-cy="nazivImovine"
                type="text"
              />
              <ValidatedField
                id="kazna-presuda"
                name="presuda"
                data-cy="presuda"
                label={translate('pravnaInformatikaApp.kazna.presuda')}
                type="select"
              >
                <option value="" key="0" />
                {presudas
                  ? presudas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/kazna" replace color="info">
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

export default KaznaUpdate;
