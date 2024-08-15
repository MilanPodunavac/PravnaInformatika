import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Optuzeni from './optuzeni';
import OptuzeniDetail from './optuzeni-detail';
import OptuzeniUpdate from './optuzeni-update';
import OptuzeniDeleteDialog from './optuzeni-delete-dialog';

const OptuzeniRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Optuzeni />} />
    <Route path="new" element={<OptuzeniUpdate />} />
    <Route path=":id">
      <Route index element={<OptuzeniDetail />} />
      <Route path="edit" element={<OptuzeniUpdate />} />
      <Route path="delete" element={<OptuzeniDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OptuzeniRoutes;
