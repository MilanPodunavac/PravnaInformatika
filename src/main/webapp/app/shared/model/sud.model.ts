import { IPresuda } from 'app/shared/model/presuda.model';
import { TipSuda } from 'app/shared/model/enumerations/tip-suda.model';

export interface ISud {
  id?: string;
  naziv?: string;
  tip?: TipSuda;
  mesto?: string;
  presudeSuds?: IPresuda[] | null;
}

export const defaultValue: Readonly<ISud> = {};
