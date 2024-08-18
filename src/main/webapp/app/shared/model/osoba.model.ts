import { IPresuda } from 'app/shared/model/presuda.model';

export interface IOsoba {
  id?: string;
  ime?: string;
  presudeSudijas?: IPresuda[] | null;
  presudeZapisnicars?: IPresuda[] | null;
  presudeTuzilacs?: IPresuda[] | null;
  presudeBranilacs?: IPresuda[] | null;
}

export const defaultValue: Readonly<IOsoba> = {};
