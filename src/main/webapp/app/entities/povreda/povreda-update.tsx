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
import { IPovreda } from 'app/shared/model/povreda.model';
import { getEntity, updateEntity, createEntity, reset } from './povreda.reducer';

export const PovredaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const radnjaPresudes = useAppSelector(state => state.radnjaPresude.entities);
  const povredaEntity = useAppSelector(state => state.povreda.entity);
  const loading = useAppSelector(state => state.povreda.loading);
  const updating = useAppSelector(state => state.povreda.updating);
  const updateSuccess = useAppSelector(state => state.povreda.updateSuccess);

  const handleClose = () => {
    navigate('/povreda');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getRadnjaPresudes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...povredaEntity,
      ...values,
      radnja: radnjaPresudes.find(it => it.id.toString() === values.radnja.toString()),
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
          ...povredaEntity,
          radnja: povredaEntity?.radnja?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.povreda.home.createOrEditLabel" data-cy="PovredaCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.povreda.home.createOrEditLabel">Create or edit a Povreda</Translate>
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
                  id="povreda-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('pravnaInformatikaApp.povreda.oruzje')}
                id="povreda-oruzje"
                name="oruzje"
                data-cy="oruzje"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.povreda.deoTela')}
                id="povreda-deoTela"
                name="deoTela"
                data-cy="deoTela"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.povreda.povrede')}
                id="povreda-povrede"
                name="povrede"
                data-cy="povrede"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="povreda-radnja"
                name="radnja"
                data-cy="radnja"
                label={translate('pravnaInformatikaApp.povreda.radnja')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/povreda" replace color="info">
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

export default PovredaUpdate;
