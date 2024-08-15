import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Presuda from './presuda';
import PresudaDetail from './presuda-detail';
import PresudaUpdate from './presuda-update';
import PresudaDeleteDialog from './presuda-delete-dialog';

const PresudaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Presuda />} />
    <Route path="new" element={<PresudaUpdate />} />
    <Route path=":id">
      <Route index element={<PresudaDetail />} />
      <Route path="edit" element={<PresudaUpdate />} />
      <Route path="delete" element={<PresudaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PresudaRoutes;
