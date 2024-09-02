import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';
import { loadMoreDataWhenScrolled, parseHeaderForLinks } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IPresuda, defaultValue } from 'app/shared/model/presuda.model';

const initialState: EntityState<IPresuda> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,

  slicnePresude: [],
  predlozeneKazne: [],
};

const apiUrl = 'api/xt/presudas';

// Actions

export const getEntities = createAsyncThunk('presuda/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IPresuda[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'presuda/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IPresuda>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'presuda/create_entity',
  async (entity: IPresuda, thunkAPI) => {
    return axios.post<IPresuda>(apiUrl, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const updateEntity = createAsyncThunk(
  'presuda/update_entity',
  async (entity: IPresuda, thunkAPI) => {
    return axios.put<IPresuda>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'presuda/partial_update_entity',
  async (entity: IPresuda, thunkAPI) => {
    return axios.patch<IPresuda>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'presuda/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    return await axios.delete<IPresuda>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

// added methods

export const createEntityFull = createAsyncThunk(
  'presuda/create_entity_full',
  async (entity: any, thunkAPI) => {
    console.log(entity);
    return axios.post<IPresuda>(`${apiUrl}/full`, entity);
  },
  { serializeError: serializeAxiosError }
);

export const getCbrReasoning = createAsyncThunk(
  'presuda/get_cbr',
  async (entity: any) => {
    console.log(entity);
    const requestUrl = `${apiUrl}/cbr`;
    return axios.post<any>(requestUrl, entity);
  },
  { serializeError: serializeAxiosError }
);

// slice

export const PresudaSlice = createEntitySlice({
  name: 'presuda',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        const { data, headers } = action.payload;
        const links = parseHeaderForLinks(headers.link);

        return {
          ...state,
          loading: false,
          links,
          entities: loadMoreDataWhenScrolled(state.entities, data, links),
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      })

      .addMatcher(isFulfilled(getCbrReasoning), (state, action) => {
        console.log(action);
        state.slicnePresude = action.payload.data.presude;
        state.predlozeneKazne = action.payload.data.kazne;
        console.log(state);
      });
  },
});

export const { reset } = PresudaSlice.actions;

// Reducer
export default PresudaSlice.reducer;
