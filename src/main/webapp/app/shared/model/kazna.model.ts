import { IPresuda } from 'app/shared/model/presuda.model';
import { TipKazne } from 'app/shared/model/enumerations/tip-kazne.model';

export interface IKazna {
  id?: string;
  tip?: TipKazne;
  duzinaPritvora?: number | null;
  uracunavanjePritvora?: boolean | null;
  kolicinaNovca?: number | null;
  primalacNovca?: string | null;
  nazivImovine?: string | null;
  presuda?: IPresuda | null;
}

export const defaultValue: Readonly<IKazna> = {
  uracunavanjePritvora: false,
};
