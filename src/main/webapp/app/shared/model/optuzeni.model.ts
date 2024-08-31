import dayjs from 'dayjs';
import { IPresuda } from 'app/shared/model/presuda.model';
import { Pol } from 'app/shared/model/enumerations/pol.model';
import { BracniStatus } from 'app/shared/model/enumerations/bracni-status.model';
import { ImovinskoStanje } from 'app/shared/model/enumerations/imovinsko-stanje.model';
import { TipObrazovanja } from 'app/shared/model/enumerations/tip-obrazovanja.model';

export interface IOptuzeni {
  id?: string;
  ime?: string;
  jmbg?: string | null;
  imeOca?: string | null;
  imeMajke?: string | null;
  pol?: Pol;
  datumRodjenja?: string | null;
  mestoRodjenja?: string | null;
  drzavaRodjenja?: string | null;
  prebivaliste?: string | null;
  drzavljanstvo?: string | null;
  bracniStatus?: BracniStatus | null;
  brojDece?: number | null;
  brojMaloletneDece?: number | null;
  imovinskoStanje?: ImovinskoStanje | null;
  obrazovanje?: TipObrazovanja | null;
  zaposlenje?: string | null;
  mestoZaposlenja?: string | null;
  presude?: IPresuda[] | null;
}

export const defaultValue: Readonly<IOptuzeni> = {};
