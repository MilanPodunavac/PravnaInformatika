import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IZakon } from 'app/shared/model/zakon.model';
import { getEntities as getZakons } from 'app/entities/zakon/zakon.reducer';
import { IClanZakona } from 'app/shared/model/clan-zakona.model';
import { getEntity, updateEntity, createEntity, reset } from './clan-zakona.reducer';

export const ClanZakonaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const zakons = useAppSelector(state => state.zakon.entities);
  const clanZakonaEntity = useAppSelector(state => state.clanZakona.entity);
  const loading = useAppSelector(state => state.clanZakona.loading);
  const updating = useAppSelector(state => state.clanZakona.updating);
  const updateSuccess = useAppSelector(state => state.clanZakona.updateSuccess);

  const handleClose = () => {
    navigate('/clan-zakona');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getZakons({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...clanZakonaEntity,
      ...values,
      zakon: zakons.find(it => it.id.toString() === values.zakon.toString()),
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
          ...clanZakonaEntity,
          zakon: clanZakonaEntity?.zakon?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pravnaInformatikaApp.clanZakona.home.createOrEditLabel" data-cy="ClanZakonaCreateUpdateHeading">
            <Translate contentKey="pravnaInformatikaApp.clanZakona.home.createOrEditLabel">Create or edit a ClanZakona</Translate>
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
                  id="clan-zakona-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('pravnaInformatikaApp.clanZakona.broj')}
                id="clan-zakona-broj"
                name="broj"
                data-cy="broj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.clanZakona.glava')}
                id="clan-zakona-glava"
                name="glava"
                data-cy="glava"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.clanZakona.naziv')}
                id="clan-zakona-naziv"
                name="naziv"
                data-cy="naziv"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.clanZakona.tekst')}
                id="clan-zakona-tekst"
                name="tekst"
                data-cy="tekst"
                type="text"
              />
              <ValidatedField
                id="clan-zakona-zakon"
                name="zakon"
                data-cy="zakon"
                label={translate('pravnaInformatikaApp.clanZakona.zakon')}
                type="select"
                required
              >
                <option value="" key="0" />
                {zakons
                  ? zakons.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/clan-zakona" replace color="info">
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

export default ClanZakonaUpdate;
