import React, { useState, useEffect, ChangeEvent } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText, Table } from 'reactstrap';
import { isNumber, TextFormat, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { getEntities as getRadnjaPresudes } from 'app/entities/radnja-presude/radnja-presude.reducer';
import { IOptuznica } from 'app/shared/model/optuznica.model';
import { getEntities as getOptuznicas } from 'app/entities/optuznica/optuznica.reducer';
import { IOsoba } from 'app/shared/model/osoba.model';
import { getEntities as getOsobas } from 'app/entities/osoba/osoba.reducer';
import { IClanZakona } from 'app/shared/model/clan-zakona.model';
import { getEntities as getClanZakonas } from 'app/entities/clan-zakona/clan-zakona.reducer';
import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { getEntities as getOptuzenis } from 'app/entities/optuzeni/optuzeni.reducer';
import { ISud } from 'app/shared/model/sud.model';
import { getEntities as getSuds } from 'app/entities/sud/sud.reducer';
import { IPresuda } from 'app/shared/model/presuda.model';
import { TipPresude } from 'app/shared/model/enumerations/tip-presude.model';
import { TipUbistva } from 'app/shared/model/enumerations/tip-ubistva.model';
import { getEntity, updateEntity, createEntity, reset, createEntityFull, getCbrReasoning } from './presuda.reducer';
import { Pol } from 'app/shared/model/enumerations/pol.model';
import { BracniStatus } from 'app/shared/model/enumerations/bracni-status.model';
import { ImovinskoStanje } from 'app/shared/model/enumerations/imovinsko-stanje.model';
import { TipObrazovanja } from 'app/shared/model/enumerations/tip-obrazovanja.model';
import { chatExtractPresuda } from 'app/modules/chat/chat';
import pdfToText from 'react-pdftotext';
import { forEach, set } from 'lodash';
import { TipSuda } from 'app/shared/model/enumerations/tip-suda.model';
import moment from 'moment';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export const PresudaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const radnjaPresudes = useAppSelector(state => state.radnjaPresude.entities);
  const optuznicas = useAppSelector(state => state.optuznica.entities);
  const osobas = useAppSelector(state => state.osoba.entities);
  const clanZakonas = useAppSelector(state => state.clanZakona.entities);
  const optuzenis = useAppSelector(state => state.optuzeni.entities);
  const suds = useAppSelector(state => state.sud.entities);
  const presudaEntity = useAppSelector(state => state.presuda.entity);
  const loading = useAppSelector(state => state.presuda.loading);
  const updating = useAppSelector(state => state.presuda.updating);
  const updateSuccess = useAppSelector(state => state.presuda.updateSuccess);

  const slicnePresude = useAppSelector(state => state.presuda.slicnePresude);
  const predlozeneKazne = useAppSelector(state => state.presuda.predlozeneKazne);

  const tipPresudeValues = Object.keys(TipPresude);
  const tipUbistvaValues = Object.keys(TipUbistva);
  const polValues = Object.keys(Pol);
  const bracniStatusValues = Object.keys(BracniStatus);
  const imovinskoStanjeValues = Object.keys(ImovinskoStanje);
  const tipObrazovanjaValues = Object.keys(TipObrazovanja);
  const tipSudaValues = Object.keys(TipSuda);

  const [kodInput, setKodInput] = useState('');
  const [datumInput, setDatumInput] = useState('');
  const [datumPritvoraInput, setDatumPritvoraInput] = useState('');
  const [tipInput, setTipInput] = useState('');
  const [brojInput, setBrojInput] = useState('');
  const [godinaInput, setGodinaInput] = useState('');
  const [pokusajInput, setPokusajInput] = useState(false);
  const [krivicaInput, setKrivicaInput] = useState(false);
  const [nacinInput, setNacinInput] = useState('');

  const [sudNazivInput, setSudNazivInput] = useState('');
  const [sudTipInput, setSudTipInput] = useState('');
  const [mestoSudaNazivInput, setMestoSudaNazivInput] = useState('');

  const [optuznicaKodInput, setOptuznicaKodInput] = useState('');
  const [optuznicaDatumInput, setOptuznicaDatumInput] = useState('');
  const [optuznicaUstanovaInput, setOptuznicaUstanovaInput] = useState('');

  const [vremeRadnjeInput, setVremeRadnjeInput] = useState('');
  const [mestoRadnjeInput, setMestoRadnjeInput] = useState('');
  const [vremeSmrtiInput, setVremeSmrtiInput] = useState('');
  const [mestoSmrtiInput, setMestoSmrtiInput] = useState('');
  const [povredeInput, setPovredeInput] = useState([]);

  const [optuzeniImeInput, setOptuzeniImeInput] = useState('');
  const [optuzeniJmbgInput, setOptuzeniJmbgInput] = useState('');
  const [optuzeniImeOcaInput, setOptuzeniImeOcaInput] = useState('');
  const [optuzeniImeMajkeInput, setOptuzeniImeMajkeInput] = useState('');
  const [optuzeniPolInput, setOptuzeniPolInput] = useState('');
  const [optuzeniDatumRodjenjaInput, setOptuzeniDatumRodjenjaInput] = useState('');
  const [optuzeniMestoRodjenjaInput, setOptuzeniMestoRodjenjaInput] = useState('');
  const [optuzeniDrzavaRodjenjaInput, setOptuzeniDrzavaRodjenjaInput] = useState('');
  const [optuzeniPrebivalisteInput, setOptuzeniPrebivalisteInput] = useState('');
  const [optuzeniDrzavljanstvoInput, setOptuzeniDrzavljanstvoInput] = useState('');
  const [optuzeniBracniStatusInput, setOptuzeniBracniStatusInput] = useState('');
  const [optuzeniBrojDeceInput, setOptuzeniBrojDeceInput] = useState('');
  const [optuzeniBrojMaloletneDeceInput, setOptuzeniBrojMaloletneDeceInput] = useState('');
  const [optuzeniImovinskoStanjeInput, setOptuzeniImovinskoStanjeInput] = useState('');
  const [optuzeniObrazovanjeInput, setOptuzeniObrazovanjeInput] = useState('');
  const [optuzeniZaposlenjeInput, setOptuzeniZaposlenjeInput] = useState('');
  const [optuzeniMestoZaposlenjeInput, setOptuzeniMestoZaposlenjaInput] = useState('');
  const [optuzeniPresudeInput, setOptuzeniPresudeInput] = useState([]);

  const [clanoviZakonaInput, setClanoviZakonaInput] = useState([]);

  const [kazneInput, setKazneInput] = useState([]);

  const [sudijaImeInput, setSudijaImeInput] = useState('');
  const [sudijaPolInput, setSudijaPolInput] = useState('');

  const [veceInput, setVeceInput] = useState([]);

  const [zapisnicarImeInput, setZapisnicarImeInput] = useState('');
  const [zapisnicarPolInput, setZapisnicarPolInput] = useState('');

  const [tuzilacImeInput, setTuzilacImeInput] = useState('');
  const [tuzilacPolInput, setTuzilacPolInput] = useState('');

  const [branilacImeInput, setBranilacImeInput] = useState('');
  const [branilacPolInput, setBranilacPolInput] = useState('');

  const [osteceniImeInput, setOsteceniImeInput] = useState('');
  const [osteceniPolInput, setOsteceniPolInput] = useState('');

  const handleClose = () => {
    navigate('/presuda');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getRadnjaPresudes({}));
    dispatch(getOptuznicas({}));
    dispatch(getOsobas({}));
    dispatch(getClanZakonas({}));
    dispatch(getOptuzenis({}));
    dispatch(getSuds({}));
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
      veces: isNew ? convertToOsobaObjects(veceInput) : mapIdList(values.veces),
      clanoviZakonas: isNew ? convertToClanoviZakonaObjects(clanoviZakonaInput) : mapIdList(values.clanoviZakonas),
      radnja: isNew
        ? {
            ...values.radnja,
            povrede: convertToPovredeObjects(povredeInput),
          }
        : radnjaPresudes.find(it => it.id.toString() === values.radnja.toString()),
      optuzeni: isNew
        ? {
            ...values.optuzeni,
            presude: convertToPresudaObjects(optuzeniPresudeInput),
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
      optuznica: isNew
        ? {
            ...values.optuznica,
          }
        : optuznicas.find(it => it.id.toString() === values.optuznica.toString()),
      osteceni: isNew
        ? {
            ...values.osteceni,
          }
        : osobas.find(it => it.id.toString() === values.osteceni.toString()),
      sud: isNew
        ? {
            ...values.sud,
          }
        : suds.find(it => it.id.toString() === values.sud.toString()),
      kazne: convertToKaznaObject(kazneInput),
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
          optuznica: presudaEntity?.optuznica?.id,
          veces: presudaEntity?.veces?.map(e => e.id.toString()),
          clanoviZakonas: presudaEntity?.clanoviZakonas?.map(e => e.id.toString()),
          optuzeni: presudaEntity?.optuzeni?.id,
          sudija: presudaEntity?.sudija?.id,
          zapisnicar: presudaEntity?.zapisnicar?.id,
          tuzilac: presudaEntity?.tuzilac?.id,
          branilac: presudaEntity?.branilac?.id,
          osteceni: presudaEntity?.osteceni?.id,
          sud: presudaEntity?.sud?.id,
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
    insertDataStates(presuda);
  };

  const insertDataStates = (presuda: any) => {
    setKodInput(presuda.kod);
    setTipInput(presuda.tip);
    setBrojInput(presuda.broj);
    setGodinaInput(presuda.godina);
    setDatumInput(presuda.datum);
    setDatumPritvoraInput(presuda.datum_pritvora);
    setPokusajInput(presuda.pokusaj);
    setKrivicaInput(presuda.krivica);
    setNacinInput(presuda.nacin);

    setSudNazivInput(presuda.sud.naziv);
    setSudTipInput(presuda.sud.tip);
    setMestoSudaNazivInput(presuda.sud.mesto);

    setOptuznicaKodInput(presuda.optuznica.kod);
    setOptuznicaDatumInput(presuda.optuznica.datum);
    setOptuznicaUstanovaInput(presuda.optuznica.ustanova ?? '');

    setSudijaImeInput(presuda.sudija.ime);
    setSudijaPolInput(presuda.sudija.pol);

    setVeceInput(presuda.vece);

    setZapisnicarImeInput(presuda.zapisnicar.ime);
    setZapisnicarPolInput(presuda.zapisnicar.pol);

    setTuzilacImeInput(presuda.tuzilac.ime);
    setTuzilacPolInput(presuda.tuzilac.pol);

    setBranilacImeInput(presuda.branilac.ime);
    setBranilacPolInput(presuda.branilac.pol);

    setOsteceniImeInput(presuda.osteceni.ime);
    setOsteceniPolInput(presuda.osteceni.pol);

    setVremeRadnjeInput(presuda.radnja.vreme_radnje);
    setMestoRadnjeInput(presuda.radnja.mesto_radnje);
    setVremeSmrtiInput(presuda.radnja.vreme_smrti ?? '');
    setMestoSmrtiInput(presuda.radnja.mesto_smrti ?? '');
    setPovredeInput(presuda.radnja.povrede);

    setOptuzeniImeInput(presuda.optuzeni.ime);
    setOptuzeniJmbgInput(presuda.optuzeni.jmbg ?? '');
    setOptuzeniImeOcaInput(presuda.optuzeni.ime_oca ?? '');
    setOptuzeniImeMajkeInput(presuda.optuzeni.ime_majke ?? '');
    setOptuzeniPolInput(presuda.optuzeni.pol);
    setOptuzeniDatumRodjenjaInput(presuda.optuzeni.datum_rodjenja ?? '');
    setOptuzeniMestoRodjenjaInput(presuda.optuzeni.mesto_rodjenja ?? '');
    setOptuzeniDrzavaRodjenjaInput(presuda.optuzeni.drzava_rodjenja ?? '');
    setOptuzeniPrebivalisteInput(presuda.optuzeni.prebivaliste ?? '');
    setOptuzeniDrzavljanstvoInput(presuda.optuzeni.drzavljanstvo ?? '');
    setOptuzeniBracniStatusInput(presuda.optuzeni.bracni_status);
    setOptuzeniBrojDeceInput(presuda.optuzeni.broj_dece ?? 0);
    setOptuzeniBrojMaloletneDeceInput(presuda.optuzeni.broj_maloletne_dece ?? 0);
    setOptuzeniImovinskoStanjeInput(presuda.optuzeni.imovinsko_stanje);
    setOptuzeniObrazovanjeInput(presuda.optuzeni.obrazovanje);
    setOptuzeniZaposlenjeInput(presuda.optuzeni.zaposlenje ?? '');
    setOptuzeniMestoZaposlenjaInput(presuda.optuzeni.mesto_zaposlenja ?? '');
    setOptuzeniPresudeInput(presuda.optuzeni.presude);

    setClanoviZakonaInput(presuda.clanovi_zakona);

    setKazneInput(presuda.kazne);
  };

  const convertToKaznaObject = (kazne: any) => {
    var kazneObjects = [];
    if (kazne) {
      kazne.forEach(function (kazna) {
        kazneObjects.push({
          id: null,
          tip: kazna.tip,
          duzinaPritvora: kazna.duzina,
          uracunavanjePritvora: kazna.uracunavanje_pritvora,
          kolicinaNovca: kazna.kolicina_novca,
          primalacNovca: kazna.primalac_novca,
          nazivImovine: kazna.naziv_imovine,
          presuda: null,
        });
      });
    }
    return kazneObjects;
  };

  const convertToPovredeObjects = (povrede: any) => {
    var povredeObjects = [];
    if (povrede) {
      povrede.forEach(function (povreda) {
        povredeObjects.push({
          id: null,
          tip: povreda.tip,
          oruzje: povreda.oruzje,
          deoTela: povreda.deo_tela,
          povrede: povreda.povrede,
          radnja: null,
        });
      });
    }
    return povredeObjects;
  };

  const convertToPresudaObjects = (presude: any) => {
    var presudeObjects = [];
    if (presude) {
      presude.forEach(function (presuda) {
        presudeObjects.push({
          id: null,
          kod: presuda.kod,
          tip: 'PRVOSTEPENI_KRIVICNI_PREDMET', //temp
          broj: presuda.broj,
          sud: {
            id: null,
            naziv: presuda.sud.naziv,
            mesto: presuda.sud.mesto,
            tip: presuda.sud.tip,
          },
          datum: moment(presuda.datum, 'YYYY-MM-DD', true).isValid() ? presuda.datum : '1901-01-01',
          clanoviZakonas: presuda.clanovi_zakona ? convertToClanoviZakonaObjects(presuda.clanovi_zakona) : [],
          kazne: presuda.kazne ? convertToKaznaObject(presuda.kazne) : [],
          krivica: true,
        });
      });
    }
    return presudeObjects;
  };

  const convertToOsobaObjects = (osobe: any) => {
    var osobeObjects = [];
    if (osobe) {
      osobe.forEach(function (osoba) {
        osobeObjects.push({
          id: null,
          ime: osoba.ime,
          pol: osoba.pol.replace('Š', 'S').replace('Ž', 'Z'),
        });
      });
    }
    return osobeObjects;
  };

  const convertToClanoviZakonaObjects = (clanovi: any) => {
    var clanoviObjects = [];
    clanovi.forEach(function (clan) {
      clanoviObjects.push({
        id: null,
        broj: clan.broj,
        zakon: {
          id: null,
          naziv: clan.zakon,
        },
        glava: 0,
        naziv: '',
      });
    });
    return clanoviObjects;
  };

  const caseBasedReasoning = () => {
    const entity = {
      datum: datumInput,
      datumPrivora: datumPritvoraInput,
      kod: kodInput,
      tip: 'PRVOSTEPENI_KRIVICNI_PREDMET',
      broj: brojInput,
      godina: godinaInput,
      pokusaj: pokusajInput,
      krivica: krivicaInput,
      nacin: nacinInput,
      clanoviZakonas: isNew ? convertToClanoviZakonaObjects(clanoviZakonaInput) : [],
      radnja: {
        mestoRadnje: mestoRadnjeInput,
        vremeRadnje: vremeRadnjeInput,
        povrede: convertToPovredeObjects(povredeInput),
      },
      sudija: {
        ime: sudijaImeInput,
        pol: sudijaPolInput,
      },
      tuzilac: {
        ime: tuzilacImeInput,
        pol: tuzilacPolInput,
      },
      branilac: {
        ime: branilacImeInput,
        pol: branilacPolInput,
      },
      sud: {
        naziv: sudNazivInput,
        tip: sudTipInput,
        mesto: mestoSudaNazivInput,
      },
      kazne: convertToKaznaObject(kazneInput),
    };

    var ret = dispatch(getCbrReasoning(entity));
    console.log(ret);
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
                label={translate('pravnaInformatikaApp.presuda.datum')}
                id="presuda-datum"
                name="datum"
                data-cy="datum"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
                value={datumInput}
                onChange={e => setDatumInput(e.target.value)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.datumPritvora')}
                id="presuda-datumPritvora"
                name="datumPritvora"
                data-cy="datumPritvora"
                type="date"
                value={datumPritvoraInput}
                onChange={e => setDatumPritvoraInput(e.target.value)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.kod')}
                id="presuda-kod"
                name="kod"
                data-cy="kod"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
                value={kodInput}
                onChange={e => setKodInput(e.target.value)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.tip')}
                id="presuda-tip"
                name="tip"
                data-cy="tip"
                type="select"
                value={tipInput}
                onChange={e => setTipInput(e.target.value)}
              >
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
                value={brojInput}
                onChange={e => setBrojInput(e.target.value)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.godina')}
                id="presuda-godina"
                name="godina"
                data-cy="godina"
                type="text"
                value={godinaInput}
                onChange={e => setGodinaInput(e.target.value)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.pokusaj')}
                id="presuda-pokusaj"
                name="pokusaj"
                data-cy="pokusaj"
                check
                type="checkbox"
                checked={pokusajInput}
                onChange={e => setPokusajInput(e.target.checked)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.krivica')}
                id="presuda-krivica"
                name="krivica"
                data-cy="krivica"
                check
                type="checkbox"
                checked={krivicaInput}
                onChange={e => setKrivicaInput(e.target.checked)}
              />
              <ValidatedField
                label={translate('pravnaInformatikaApp.presuda.nacin')}
                id="presuda-nacin"
                name="nacin"
                data-cy="nacin"
                type="select"
                value={nacinInput}
                onChange={e => setNacinInput(e.target.value)}
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
                  value={vremeRadnjeInput}
                  onChange={e => setVremeRadnjeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.mestoRadnje')}
                  id="presuda-radnja-mestoRadnje"
                  name="radnja.mestoRadnje"
                  data-cy="radnja.mestoRadnje"
                  type="text"
                  value={mestoRadnjeInput}
                  onChange={e => setMestoRadnjeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.vremeSmrti')}
                  id="presuda-radnja-vremeSmrti"
                  name="radnja.vremeSmrti"
                  data-cy="radnja.vremeSmrti"
                  type="date"
                  value={vremeSmrtiInput}
                  onChange={e => setVremeSmrtiInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.radnjaPresude.mestoSmrti')}
                  id="presuda-radnja-mestoSmrti"
                  name="radnja.mestoSmrti"
                  data-cy="radnja.mestoSmrti"
                  type="text"
                  value={mestoSmrtiInput}
                  onChange={e => setMestoSmrtiInput(e.target.value)}
                />
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <div className="table-responsive">
                {povredeInput && povredeInput.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.povreda.oruzje">Oruzje</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.povreda.deoTela">Deo Tela</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.povreda.povrede">Povrede</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {povredeInput.map((povreda, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{povreda.oruzje}</td>
                          <td>{povreda.deo_tela}</td>
                          <td>{povreda.povrede}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.povreda.home.notFound">No Povredas found</Translate>
                    </div>
                  )
                )}
              </div>
              {!isNew && (
                <ValidatedField
                  id="presuda-optuznica"
                  name="optuznica"
                  data-cy="optuznica"
                  label={translate('pravnaInformatikaApp.presuda.optuznica')}
                  type="select"
                  disabled={!isNew}
                  required
                >
                  <option value="" key="0" />
                  {optuznicas
                    ? optuznicas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuznica.kod')}
                  id="presuda-optuznica-kod"
                  name="optuznica.kod"
                  data-cy="optuznica.kod"
                  type="text"
                  value={optuznicaKodInput}
                  onChange={e => setOptuznicaKodInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuznica.datum')}
                  id="presuda-optuznica-datum"
                  name="optuznica.datum"
                  data-cy="optuznica.datum"
                  type="date"
                  value={optuznicaDatumInput}
                  onChange={e => setOptuznicaDatumInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuznica.ustanova')}
                  id="presuda-optuznica-ustanova"
                  name="optuznica.ustanova"
                  data-cy="optuznica.ustanova"
                  type="text"
                  value={optuznicaUstanovaInput}
                  onChange={e => setOptuznicaUstanovaInput(e.target.value)}
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
                  value={optuzeniImeInput}
                  onChange={e => setOptuzeniImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.jmbg')}
                  id="presuda-optuzeni-jmbg"
                  name="optuzeni.jmbg"
                  data-cy="optuzeni.jmbg"
                  type="text"
                  value={optuzeniJmbgInput}
                  onChange={e => setOptuzeniJmbgInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imeOca')}
                  id="presuda-optuzeni-imeOca"
                  name="optuzeni.imeOca"
                  data-cy="optuzeni.imeOca"
                  type="text"
                  value={optuzeniImeOcaInput}
                  onChange={e => setOptuzeniImeOcaInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imeMajke')}
                  id="presuda-optuzeni-imeMajke"
                  name="optuzeni.imeMajke"
                  data-cy="optuzeni.imeMajke"
                  type="text"
                  value={optuzeniImeMajkeInput}
                  onChange={e => setOptuzeniImeMajkeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.pol')}
                  id="presuda-optuzeni-pol"
                  name="optuzeni.pol"
                  data-cy="optuzeni.pol"
                  type="select"
                  value={optuzeniPolInput}
                  onChange={e => setOptuzeniPolInput(e.target.value)}
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
                  value={optuzeniDatumRodjenjaInput}
                  onChange={e => setOptuzeniDatumRodjenjaInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.mestoRodjenja')}
                  id="presuda-optuzeni-mestoRodjenja"
                  name="optuzeni.mestoRodjenja"
                  data-cy="optuzeni.mestoRodjenja"
                  type="text"
                  value={optuzeniMestoRodjenjaInput}
                  onChange={e => setOptuzeniMestoRodjenjaInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.drzavaRodjenja')}
                  id="presuda-optuzeni-drzavaRodjenja"
                  name="optuzeni.drzavaRodjenja"
                  data-cy="optuzeni.drzavaRodjenja"
                  type="text"
                  value={optuzeniDrzavaRodjenjaInput}
                  onChange={e => setOptuzeniDrzavaRodjenjaInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.prebivaliste')}
                  id="presuda-optuzeni-prebivaliste"
                  name="optuzeni.prebivaliste"
                  data-cy="optuzeni.prebivaliste"
                  type="text"
                  value={optuzeniPrebivalisteInput}
                  onChange={e => setOptuzeniPrebivalisteInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.drzavljanstvo')}
                  id="presuda-optuzeni-drzavljanstvo"
                  name="optuzeni.drzavljanstvo"
                  data-cy="optuzeni.drzavljanstvo"
                  type="text"
                  value={optuzeniDrzavljanstvoInput}
                  onChange={e => setOptuzeniDrzavljanstvoInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.bracniStatus')}
                  id="presuda-optuzeni-bracniStatus"
                  name="optuzeni.bracniStatus"
                  data-cy="optuzeni.bracniStatus"
                  type="select"
                  value={optuzeniBracniStatusInput}
                  onChange={e => setOptuzeniBracniStatusInput(e.target.value)}
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
                  value={optuzeniBrojDeceInput}
                  onChange={e => setOptuzeniBrojDeceInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.brojMaloletneDece')}
                  id="presuda-optuzeni-brojMaloletneDece"
                  name="optuzeni.brojMaloletneDece"
                  data-cy="optuzeni.brojMaloletneDece"
                  type="text"
                  value={optuzeniBrojMaloletneDeceInput}
                  onChange={e => setOptuzeniBrojMaloletneDeceInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.imovinskoStanje')}
                  id="presuda-optuzeni-imovinskoStanje"
                  name="optuzeni.imovinskoStanje"
                  data-cy="optuzeni.imovinskoStanje"
                  type="select"
                  value={optuzeniImovinskoStanjeInput}
                  onChange={e => setOptuzeniImovinskoStanjeInput(e.target.value)}
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
                  value={optuzeniObrazovanjeInput}
                  onChange={e => setOptuzeniObrazovanjeInput(e.target.value)}
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
                  value={optuzeniZaposlenjeInput}
                  onChange={e => setOptuzeniZaposlenjeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.optuzeni.mestoZaposlenja')}
                  id="presuda-optuzeni-mestoZaposlenja"
                  name="optuzeni.mestoZaposlenja"
                  data-cy="optuzeni.mestoZaposlenja"
                  type="text"
                  value={optuzeniMestoZaposlenjeInput}
                  onChange={e => setOptuzeniMestoZaposlenjaInput(e.target.value)}
                />
              )}
              <div className="table-responsive">
                {optuzeniPresudeInput && optuzeniPresudeInput.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.kod">Kod</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.datum">Datum</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.sud.naziv">Naziv Suda</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.clanZakona.zakon">Clan Zakona</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.tip">Kazna</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.duzinaPritvora">Duzina</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.kolicinaNovca">Kolicina</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {optuzeniPresudeInput.map((presuda, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{presuda.kod}</td>
                          <td>{presuda.datum}</td>
                          <td>{presuda.sud ? presuda.sud.naziv : ''}</td>
                          <td>{presuda.clanovi_zakona ? presuda.clanovi_zakona[0].broj + ', ' + presuda.clanovi_zakona[0].zakon : ''}</td>
                          <td>{presuda.kazne ? presuda.kazne[0].tip : ''}</td>
                          <td>{presuda.kazne ? presuda.kazne[0].duzina : ''}</td>
                          <td>{presuda.kazne ? presuda.kazne[0].kolicina_novca : ''}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.presuda.home.notFound">No Povredas found</Translate>
                    </div>
                  )
                )}
              </div>
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
                  value={sudijaImeInput}
                  onChange={e => setSudijaImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.pol')}
                  id="presuda-sudija-pol"
                  name="sudija.pol"
                  data-cy="sudija.pol"
                  type="select"
                  value={sudijaPolInput}
                  onChange={e => setSudijaPolInput(e.target.value)}
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {!isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.presuda.vece')}
                  id="presuda-vece"
                  data-cy="vece"
                  type="select"
                  multiple
                  name="veces"
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
              )}
              <div className="table-responsive">
                {veceInput && veceInput.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.osoba.ime">Ime</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.osoba.pol">Pol</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {veceInput.map((osoba, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{osoba.ime}</td>
                          <td>{osoba.pol}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.osoba.home.notFound">No Osobas found</Translate>
                    </div>
                  )
                )}
              </div>
              {!isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.presuda.clanoviZakona')}
                  id="presuda-clanoviZakona"
                  data-cy="clanoviZakona"
                  type="select"
                  multiple
                  name="clanoviZakonas"
                >
                  <option value="" key="0" />
                  {clanZakonas
                    ? clanZakonas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              <div className="table-responsive">
                {clanoviZakonaInput && clanoviZakonaInput.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.clanZakona.broj">Broj</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.zakon.naziv">Zakon</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {clanoviZakonaInput.map((clanZakona, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{clanZakona.broj}</td>
                          <td>{clanZakona.zakon}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.clanZakona.home.notFound">No ClanZakonas found</Translate>
                    </div>
                  )
                )}
              </div>
              {!isNew && (
                <ValidatedField
                  id="presuda-zapisnicar"
                  name="zapisnicar"
                  data-cy="zapisnicar"
                  label={translate('pravnaInformatikaApp.presuda.zapisnicar')}
                  type="select"
                  disabled={!isNew}
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
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-zapisnicar-ime"
                  name="zapisnicar.ime"
                  data-cy="zapisnicar.ime"
                  type="text"
                  value={zapisnicarImeInput}
                  onChange={e => setZapisnicarImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.pol')}
                  id="presuda-zapisnicar-pol"
                  name="zapisnicar.pol"
                  data-cy="zapisnicar.pol"
                  type="select"
                  value={zapisnicarPolInput}
                  onChange={e => setZapisnicarPolInput(e.target.value)}
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {!isNew && (
                <ValidatedField
                  id="presuda-tuzilac"
                  name="tuzilac"
                  data-cy="tuzilac"
                  label={translate('pravnaInformatikaApp.presuda.tuzilac')}
                  type="select"
                  disabled={!isNew}
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
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-tuzilac-ime"
                  name="tuzilac.ime"
                  data-cy="tuzilac.ime"
                  type="text"
                  value={tuzilacImeInput}
                  onChange={e => setTuzilacImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.pol')}
                  id="presuda-tuzilac-pol"
                  name="tuzilac.pol"
                  data-cy="tuzilac.pol"
                  type="select"
                  value={tuzilacPolInput}
                  onChange={e => setTuzilacPolInput(e.target.value)}
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {!isNew && (
                <ValidatedField
                  id="presuda-branilac"
                  name="branilac"
                  data-cy="branilac"
                  label={translate('pravnaInformatikaApp.presuda.branilac')}
                  type="select"
                  disabled={!isNew}
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
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-branilac-ime"
                  name="branilac.ime"
                  data-cy="branilac.ime"
                  type="text"
                  value={branilacImeInput}
                  onChange={e => setBranilacImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.pol')}
                  id="presuda-branilac-pol"
                  name="branilac.pol"
                  data-cy="branilac.pol"
                  type="select"
                  value={branilacPolInput}
                  onChange={e => setBranilacPolInput(e.target.value)}
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {!isNew && (
                <ValidatedField
                  id="presuda-osteceni"
                  name="osteceni"
                  data-cy="osteceni"
                  label={translate('pravnaInformatikaApp.presuda.osteceni')}
                  type="select"
                  disabled={!isNew}
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
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.ime')}
                  id="presuda-osteceni-ime"
                  name="osteceni.ime"
                  data-cy="osteceni.ime"
                  type="text"
                  value={osteceniImeInput}
                  onChange={e => setOsteceniImeInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.osoba.pol')}
                  id="presuda-osteceni-pol"
                  name="osteceni.pol"
                  data-cy="osteceni.pol"
                  type="select"
                  value={osteceniPolInput}
                  onChange={e => setOsteceniPolInput(e.target.value)}
                >
                  {polValues.map(pol => (
                    <option value={pol} key={pol}>
                      {translate('pravnaInformatikaApp.Pol.' + pol)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {!isNew && (
                <ValidatedField
                  id="presuda-sud"
                  name="sud"
                  data-cy="sud"
                  label={translate('pravnaInformatikaApp.presuda.sud')}
                  type="select"
                  disabled={!isNew}
                >
                  <option value="" key="0" />
                  {suds
                    ? suds.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.sud.naziv')}
                  id="presuda-sud-naziv"
                  name="sud.naziv"
                  data-cy="sud.naziv"
                  type="text"
                  value={sudNazivInput}
                  onChange={e => setSudNazivInput(e.target.value)}
                />
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.sud.tip')}
                  id="presuda-sud-tip"
                  name="sud.tip"
                  data-cy="sud.tip"
                  type="select"
                  value={sudTipInput}
                  onChange={e => setSudTipInput(e.target.value)}
                >
                  {tipSudaValues.map(tipSuda => (
                    <option value={tipSuda} key={tipSuda}>
                      {translate('pravnaInformatikaApp.TipSuda.' + tipSuda)}
                    </option>
                  ))}
                </ValidatedField>
              )}
              {isNew && (
                <ValidatedField
                  label={translate('pravnaInformatikaApp.sud.mesto')}
                  id="presuda-sud-mesto"
                  name="sud.mesto"
                  data-cy="sud.mesto"
                  type="text"
                  value={mestoSudaNazivInput}
                  onChange={e => setMestoSudaNazivInput(e.target.value)}
                />
              )}
              <div className="table-responsive">
                {slicnePresude && slicnePresude.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.datum">Datum</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.datumPritvora">Datum Pritvora</Translate>{' '}
                          <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.kod">Kod</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.pokusaj">Pokusaj</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.presuda.nacin">Nacin</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {slicnePresude.map((presuda, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>
                            <Button tag={Link} to={`/presuda/${presuda.id}`} color="link" size="sm">
                              {presuda.id}
                            </Button>
                          </td>
                          <td>{presuda.datum ? <TextFormat type="date" value={presuda.datum} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                          <td>
                            {presuda.datumPritvora ? (
                              <TextFormat type="date" value={presuda.datumPritvora} format={APP_LOCAL_DATE_FORMAT} />
                            ) : null}
                          </td>
                          <td>{presuda.kod}</td>
                          <td>{presuda.pokusaj ? 'true' : 'false'}</td>
                          <td>
                            <Translate contentKey={`pravnaInformatikaApp.TipUbistva.${presuda.nacin}`} />
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.presuda.home.notFound">No Presudas found</Translate>
                    </div>
                  )
                )}
              </div>
              <div className="table-responsive">
                {predlozeneKazne && predlozeneKazne.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.tip">Tip</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.duzinaPritvora">Duzina Pritvora</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.uracunavanjePritvora">Uracunavanje Pritvora</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.kolicinaNovca">Kolicina Novca</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.primalacNovca">Primalac Novca</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.nazivImovine">Naziv Imovine</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {predlozeneKazne.map((kazna, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>
                            <Translate contentKey={`pravnaInformatikaApp.TipKazne.${kazna.tip}`} />
                          </td>
                          <td>{kazna.duzinaPritvora}</td>
                          <td>{kazna.uracunavanjePrivora ? 'true' : 'false'}</td>
                          <td>{kazna.kolicinaNovca}</td>
                          <td>{kazna.primalacNovca}</td>
                          <td>{kazna.nazivImovine}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.kazna.home.notFound">No Kaznas found</Translate>
                    </div>
                  )
                )}
              </div>
              <div className="table-responsive">
                {kazneInput && kazneInput.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.tip">Tip</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.duzinaPritvora">Duzina Pritvora</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.uracunavanjePritvora">Uracunavanje Pritvora</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.kolicinaNovca">Kolicina Novca</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.primalacNovca">Primalac Novca</Translate>
                        </th>
                        <th>
                          <Translate contentKey="pravnaInformatikaApp.kazna.nazivImovine">Naziv Imovine</Translate>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      {kazneInput.map((kazna, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>
                            <Translate contentKey={`pravnaInformatikaApp.TipKazne.${kazna.tip}`} />
                          </td>
                          <td>{kazna.duzina}</td>
                          <td>{kazna.uracunavanje_pritvora ? 'true' : 'false'}</td>
                          <td>{kazna.kolicina_novca}</td>
                          <td>{kazna.primalac_novca}</td>
                          <td>{kazna.naziv_imovine}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <Translate contentKey="pravnaInformatikaApp.kazna.home.notFound">No Kaznas found</Translate>
                    </div>
                  )
                )}
              </div>
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
              &nbsp;
              <Button color="info" id="cbr-entity" data-cy="entityCbrButton" disabled={updating} onClick={caseBasedReasoning}>
                &nbsp;
                <Translate contentKey="entity.action.cbr">CBR</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PresudaUpdate;
