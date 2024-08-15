import { IZakon } from 'app/shared/model/zakon.model';

export interface IClanZakona {
  id?: string;
  broj?: number;
  glava?: number;
  naziv?: string;
  tekst?: string | null;
  zakon?: IZakon;
}

export const defaultValue: Readonly<IClanZakona> = {};
