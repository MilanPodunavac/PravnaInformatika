import { IZakon } from 'app/shared/model/zakon.model';
import { IPresuda } from 'app/shared/model/presuda.model';

export interface IClanZakona {
  id?: string;
  broj?: number;
  glava?: number;
  naziv?: string;
  tekst?: string | null;
  zakon?: IZakon;
  presudes?: IPresuda[] | null;
}

export const defaultValue: Readonly<IClanZakona> = {};
