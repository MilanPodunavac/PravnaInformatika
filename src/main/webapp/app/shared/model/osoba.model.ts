import { IPresuda } from 'app/shared/model/presuda.model';
import { Pol } from 'app/shared/model/enumerations/pol.model';

export interface IOsoba {
  id?: string;
  ime?: string;
  pol?: Pol;
  presudeSudijas?: IPresuda[] | null;
  presudeZapisnicars?: IPresuda[] | null;
  presudeTuzilacs?: IPresuda[] | null;
  presudeBranilacs?: IPresuda[] | null;
  presudeOstecenis?: IPresuda[] | null;
}

export const defaultValue: Readonly<IOsoba> = {};
