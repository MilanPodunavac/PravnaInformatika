import React, { useState, useEffect, ChangeEvent } from 'react';
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
import { getEntity, updateEntity, createEntity, reset, createEntityFull } from './presuda.reducer';
import { Pol } from 'app/shared/model/enumerations/pol.model';
import { BracniStatus } from 'app/shared/model/enumerations/bracni-status.model';
import { ImovinskoStanje } from 'app/shared/model/enumerations/imovinsko-stanje.model';
import { TipObrazovanja } from 'app/shared/model/enumerations/tip-obrazovanja.model';
import { chatExtractPresuda } from 'app/modules/chat/chat';
import pdfToText from 'react-pdftotext';

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
  const polValues = Object.keys(Pol);
  const bracniStatusValues = Object.keys(BracniStatus);
  const imovinskoStanjeValues = Object.keys(ImovinskoStanje);
  const tipObrazovanjaValues = Object.keys(TipObrazovanja);

  const [tekstInput, setTekstInput] = useState('');

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
      radnja: isNew
        ? {
            ...values.radnja,
          }
        : radnjaPresudes.find(it => it.id.toString() === values.radnja.toString()),
      optuzeni: isNew
        ? {
            ...values.optuzeni,
          }
        : optuzenis.find(it => it.id.toString() === values.optuzeni.toString()),
      sudija: isNew
        ? {
            ...values.sudija,
          }
        : osobas.find(it => it.id.toString() === values.sudija.toString()),
      zapisnicar: isNew
        ? {
            ...values.zapisnicar,
          }
        : osobas.find(it => it.id.toString() === values.zapisnicar.toString()),
      tuzilac: isNew
        ? {
            ...values.tuzilac,
          }
        : osobas.find(it => it.id.toString() === values.tuzilac.toString()),
      branilac: isNew
        ? {
            ...values.branilac,
          }
        : osobas.find(it => it.id.toString() === values.branilac.toString()),
    };

    if (isNew) {
      //dispatch(createEntity(entity));
      dispatch(createEntityFull(entity));
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

  function extractText(event: ChangeEvent<HTMLInputElement>): void {
    const file = event.target.files[0];
    pdfToText(file)
      .then(text => handlePresudaText(text))
      .catch(error => console.error(error));
  }

  const handlePresudaText = async (text: string) => {
    console.log(text);
    var presuda = JSON.parse(await chatExtractPresuda(text));
    console.log(presuda);
    console.log(presuda.godina);
    console.log(presuda.sud.naziv);
    setTekstInput(presuda);
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
      {isNew && <input type="file" accept="application/pdf" onChange={extractText} />}
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
                value={tekstInput}
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
              {!isNew && (
                <ValidatedField
                  id="presuda-radnja"
                  name="radnja"
                  data-cy="radnja"
                  label={translate('pravnaInformatikaApp.presuda.radnja')}
                  type="select"
                  disabled={!isNew}
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
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.vremeRadnje')}
                  id="presuda-radnja-vremeRadnje"
                  name="radnja.vremeRadnje"
                  data-cy="radnja.vremeRadnje"
                  type="date"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.mestoRadnje')}
                  id="presuda-radnja-mestoRadnje"
                  name="radnja.mestoRadnje"
                  data-cy="radnja.mestoRadnje"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.bitneNapomene')}
                  id="presuda-radnja-bitneNapomene"
                  name="radnja.bitneNapomene"
                  data-cy="radnja.bitneNapomene"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.vremeSmrti')}
                  id="presuda-radnja-vremeSmrti"
                  name="radnja.vremeSmrti"
                  data-cy="radnja.vremeSmrti"
                  type="date"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.mestoSmrti')}
                  id="presuda-radnja-mestoSmrti"
                  name="radnja.mestoSmrti"
                  data-cy="radnja.mestoSmrti"
                  type="text"
                />
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {!isNew && (
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
                          {otherEntity.ime}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.ime')}
                  id="presuda-optuzeni-ime"
                  name="optuzeni.ime"
                  data-cy="optuzeni.ime"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.jmbg')}
                  id="presuda-optuzeni-jmbg"
                  name="optuzeni.jmbg"
                  data-cy="optuzeni.jmbg"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imeOca')}
                  id="presuda-optuzeni-imeOca"
                  name="optuzeni.imeOca"
                  data-cy="optuzeni.imeOca"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imeMajke')}
                  id="presuda-optuzeni-imeMajke"
                  name="optuzeni.imeMajke"
                  data-cy="optuzeni.imeMajke"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.pol')}
                  id="presuda-optuzeni-pol"
                  name="optuzeni.pol"
                  data-cy="optuzeni.pol"
                  type="select"
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.datumRodjenja')}
                  id="presuda-optuzeni-datumRodjenja"
                  name="optuzeni.datumRodjenja"
                  data-cy="optuzeni.datumRodjenja"
                  type="date"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.mestoRodjenja')}
                  id="presuda-optuzeni-mestoRodjenja"
                  name="optuzeni.mestoRodjenja"
                  data-cy="optuzeni.mestoRodjenja"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.drzavaRodjenja')}
                  id="presuda-optuzeni-drzavaRodjenja"
                  name="optuzeni.drzavaRodjenja"
                  data-cy="optuzeni.drzavaRodjenja"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.prebivaliste')}
                  id="presuda-optuzeni-prebivaliste"
                  name="optuzeni.prebivaliste"
                  data-cy="optuzeni.prebivaliste"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.bracniStatus')}
                  id="presuda-optuzeni-bracniStatus"
                  name="optuzeni.bracniStatus"
                  data-cy="optuzeni.bracniStatus"
                  type="select"
                >
                  {bracniStatusValues.map(bracniStatus => (
                    <option value={bracniStatus} key={bracniStatus}>
                      {translate('pravnaInformatikaApp.BracniStatus.' + bracniStatus)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.brojDece')}
                  id="presuda-optuzeni-brojDece"
                  name="optuzeni.brojDece"
                  data-cy="optuzeni.brojDece"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.brojMaloletneDece')}
                  id="presuda-optuzeni-brojMaloletneDece"
                  name="optuzeni.brojMaloletneDece"
                  data-cy="optuzeni.brojMaloletneDece"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imovinskoStanje')}
                  id="presuda-optuzeni-imovinskoStanje"
                  name="optuzeni.imovinskoStanje"
                  data-cy="optuzeni.imovinskoStanje"
                  type="select"
                >
                  {imovinskoStanjeValues.map(imovinskoStanje => (
                    <option value={imovinskoStanje} key={imovinskoStanje}>
                      {translate('pravnaInformatikaApp.ImovinskoStanje.' + imovinskoStanje)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.obrazovanje')}
                  id="presuda-optuzeni-obrazovanje"
                  name="optuzeni.obrazovanje"
                  data-cy="optuzeni.obrazovanje"
                  type="select"
                >
                  {tipObrazovanjaValues.map(tipObrazovanja => (
                    <option value={tipObrazovanja} key={tipObrazovanja}>
                      {translate('pravnaInformatikaApp.TipObrazovanja.' + tipObrazovanja)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.zaposlenje')}
                  id="presuda-optuzeni-zaposlenje"
                  name="optuzeni.zaposlenje"
                  data-cy="optuzeni.zaposlenje"
                  type="text"
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.mestoZaposlenja')}
                  id="presuda-optuzeni-mestoZaposlenja"
                  name="optuzeni.mestoZaposlenja"
                  data-cy="optuzeni.mestoZaposlenja"
                  type="text"
                />
              )}
              {!isNew && (
                <ValidatedField
                  id="presuda-sudija"
                  name="sudija"
                  data-cy="sudija"
                  label={translate('pravnaInformatikaApp.presuda.sudija')}
                  type="select"
                  disabled={!isNew}
                  required
                >
                  <option value="" key="0" />
                  {osobas
                    ? osobas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.ime}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-sudija-ime"
                  name="sudija.ime"
                  data-cy="sudija.ime"
                  type="text"
                />
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {!isNew && (
                <ValidatedField
                  id="presuda-zapisnicar"
                  name="zapisnicar"
                  data-cy="zapisnicar"
                  label={translate('pravnaInformatikaApp.presuda.zapisnicar')}
                  type="select"
                  disabled={!isNew}
                  required
                >
                  <option value="" key="0" />
                  {osobas
                    ? osobas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.ime}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-zapisnicar-ime"
                  name="zapisnicar.ime"
                  data-cy="zapisnicar.ime"
                  type="text"
                />
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {!isNew && (
                <ValidatedField
                  id="presuda-tuzilac"
                  name="tuzilac"
                  data-cy="tuzilac"
                  label={translate('pravnaInformatikaApp.presuda.tuzilac')}
                  type="select"
                  disabled={!isNew}
                  required
                >
                  <option value="" key="0" />
                  {osobas
                    ? osobas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.ime}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-tuzilac-ime"
                  name="tuzilac.ime"
                  data-cy="tuzilac.ime"
                  type="text"
                />
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {!isNew && (
                <ValidatedField
                  id="presuda-branilac"
                  name="branilac"
                  data-cy="branilac"
                  label={translate('pravnaInformatikaApp.presuda.branilac')}
                  type="select"
                  disabled={!isNew}
                  required
                >
                  <option value="" key="0" />
                  {osobas
                    ? osobas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.ime}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-branilac-ime"
                  name="branilac.ime"
                  data-cy="branilac.ime"
                  type="text"
                />
              )}
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
