import dayjs from 'dayjs';
import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';
import { IKazna } from 'app/shared/model/kazna.model';
import { IOptuzeni } from 'app/shared/model/optuzeni.model';
import { IOsoba } from 'app/shared/model/osoba.model';
import { TipPresude } from 'app/shared/model/enumerations/tip-presude.model';
import { TipUbistva } from 'app/shared/model/enumerations/tip-ubistva.model';

export interface IPresuda {
  id?: string;
  tekst?: string | null;
  datum?: string;
  datumObjave?: string | null;
  datumPritvora?: string | null;
  tip?: TipPresude;
  broj?: number;
  godina?: number;
  optuznica?: string | null;
  datumOptuznice?: string | null;
  pokusaj?: boolean | null;
  krivica?: boolean | null;
  nacin?: TipUbistva | null;
  radnja?: IRadnjaPresude;
  kaznes?: IKazna[] | null;
  optuzeni?: IOptuzeni;
  sudija?: IOsoba;
  zapisnicar?: IOsoba;
  tuzilac?: IOsoba;
  branilac?: IOsoba;
  veces?: IOsoba[] | null;
}

export const defaultValue: Readonly<IPresuda> = {
  pokusaj: false,
  krivica: false,
};
