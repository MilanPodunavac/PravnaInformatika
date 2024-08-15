import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Sud from './sud';
import SudDetail from './sud-detail';
import SudUpdate from './sud-update';
import SudDeleteDialog from './sud-delete-dialog';

const SudRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Sud />} />
    <Route path="new" element={<SudUpdate />} />
    <Route path=":id">
      <Route index element={<SudDetail />} />
      <Route path="edit" element={<SudUpdate />} />
      <Route path="delete" element={<SudDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SudRoutes;
