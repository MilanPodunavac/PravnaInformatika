import { IRadnjaPresude } from 'app/shared/model/radnja-presude.model';

export interface IPovreda {
  id?: string;
  oruzje?: string;
  deoTela?: string;
  povrede?: string;
  radnja?: IRadnjaPresude;
}

export const defaultValue: Readonly<IPovreda> = {};
