import { IClanZakona } from 'app/shared/model/clan-zakona.model';

export interface IZakon {
  id?: string;
  naziv?: string;
  clanovis?: IClanZakona[] | null;
}

export const defaultValue: Readonly<IZakon> = {};
