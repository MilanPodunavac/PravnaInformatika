import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Optuznica from './optuznica';
import OptuznicaDetail from './optuznica-detail';
import OptuznicaUpdate from './optuznica-update';
import OptuznicaDeleteDialog from './optuznica-delete-dialog';

const OptuznicaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Optuznica />} />
    <Route path="new" element={<OptuznicaUpdate />} />
    <Route path=":id">
      <Route index element={<OptuznicaDetail />} />
      <Route path="edit" element={<OptuznicaUpdate />} />
      <Route path="delete" element={<OptuznicaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OptuznicaRoutes;
