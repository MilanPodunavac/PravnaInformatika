import dayjs from 'dayjs';
import { IPresuda } from 'app/shared/model/presuda.model';

export interface IOptuznica {
  id?: string;
  kod?: string;
  datum?: string;
  ustanova?: string | null;
  presuda?: IPresuda | null;
}

export const defaultValue: Readonly<IOptuznica> = {};
