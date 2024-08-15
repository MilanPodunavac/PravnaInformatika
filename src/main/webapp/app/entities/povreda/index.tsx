import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Povreda from './povreda';
import PovredaDetail from './povreda-detail';
import PovredaUpdate from './povreda-update';
import PovredaDeleteDialog from './povreda-delete-dialog';

const PovredaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Povreda />} />
    <Route path="new" element={<PovredaUpdate />} />
    <Route path=":id">
      <Route index element={<PovredaDetail />} />
      <Route path="edit" element={<PovredaUpdate />} />
      <Route path="delete" element={<PovredaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PovredaRoutes;
