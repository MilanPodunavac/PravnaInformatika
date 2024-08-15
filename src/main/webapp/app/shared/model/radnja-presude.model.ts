import dayjs from 'dayjs';
import { IPovreda } from 'app/shared/model/povreda.model';
import { IPresuda } from 'app/shared/model/presuda.model';

export interface IRadnjaPresude {
  id?: string;
  vremeRadnje?: string;
  mestoRadnje?: string;
  bitneNapomene?: string | null;
  mestoSmrti?: string | null;
  vremeSmrti?: string | null;
  povredes?: IPovreda[] | null;
  presuda?: IPresuda | null;
}

export const defaultValue: Readonly<IRadnjaPresude> = {};
