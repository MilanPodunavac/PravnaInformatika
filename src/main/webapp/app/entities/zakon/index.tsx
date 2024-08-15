import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Zakon from './zakon';
import ZakonDetail from './zakon-detail';
import ZakonUpdate from './zakon-update';
import ZakonDeleteDialog from './zakon-delete-dialog';

const ZakonRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Zakon />} />
    <Route path="new" element={<ZakonUpdate />} />
    <Route path=":id">
      <Route index element={<ZakonDetail />} />
      <Route path="edit" element={<ZakonUpdate />} />
      <Route path="delete" element={<ZakonDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ZakonRoutes;
