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
import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { getEntity, updateEntity, createEntity, reset } from './radnja-presude.reducer';

export const RadnjaPresudeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const presudas = useAppSelector(state => state.presuda.entities);
  const radnjaPresudeEntity = useAppSelector(state => state.radnjaPresude.entity);
  const loading = useAppSelector(state => state.radnjaPresude.loading);
  const updating = useAppSelector(state => state.radnjaPresude.updating);
  const updateSuccess = useAppSelector(state => state.radnjaPresude.updateSuccess);

  const handleClose = () => {
    navigate('/radnja-presude');
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
      ...radnjaPresudeEntity,
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
          ...radnjaPresudeEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.radnjaPresude.home.createOrEditLabel" data-cy="RadnjaPresudeCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.radnjaPresude.home.createOrEditLabel">Create or edit a RadnjaPresude</Translate>
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
                  id="radnja-presude-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('pravnaInformatikaApp.radnjaPresude.vremeRadnje')}
                id="radnja-presude-vremeRadnje"
                name="vremeRadnje"
                data-cy="vremeRadnje"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.radnjaPresude.mestoRadnje')}
                id="radnja-presude-mestoRadnje"
                name="mestoRadnje"
                data-cy="mestoRadnje"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.radnjaPresude.mestoSmrti')}
                id="radnja-presude-mestoSmrti"
                name="mestoSmrti"
                data-cy="mestoSmrti"
                type="text"
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.radnjaPresude.vremeSmrti')}
                id="radnja-presude-vremeSmrti"
                name="vremeSmrti"
                data-cy="vremeSmrti"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/radnja-presude" replace color="info">
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

export default RadnjaPresudeUpdate;
