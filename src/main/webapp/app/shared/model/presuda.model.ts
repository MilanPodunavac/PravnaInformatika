import dayjs from 'dayjs';
import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { IOptuznica } from 'app/shared/model/optuznica.model';
import { IKazna } from 'app/shared/model/kazna.model';
import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { IOsoba } from 'app/shared/model/osoba.model';
import { ISud } from 'app/shared/model/sud.model';
import { TipPresude } from 'app/shared/model/enumerations/tip-presude.model';
import { TipUbistva } from 'app/shared/model/enumerations/tip-ubistva.model';

export interface IPresuda {
  id?: string;
  datum?: string;
  datumPritvora?: string | null;
  kod?: string;
  tip?: TipPresude;
  broj?: number | null;
  godina?: number | null;
  pokusaj?: boolean | null;
  krivica?: boolean | null;
  nacin?: TipUbistva | null;
  radnja?: IRadnjaPresude;
  optuznica?: IOptuznica;
  kaznes?: IKazna[] | null;
  optuzeni?: IOptuzeni | null;
  sudija?: IOsoba | null;
  zapisnicar?: IOsoba | null;
  tuzilac?: IOsoba | null;
  branilac?: IOsoba | null;
  osteceni?: IOsoba | null;
  sud?: ISud | null;
}

export const defaultValue: Readonly<IPresuda> = {
  pokusaj: false,
  krivica: false,
};
