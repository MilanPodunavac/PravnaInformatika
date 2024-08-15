import { TipSuda } from 'app/shared/model/enumerations/tip-suda.model';

export interface ISud {
  id?: string;
  tip?: TipSuda;
  naselje?: string;
}

export const defaultValue: Readonly<ISud> = {};
