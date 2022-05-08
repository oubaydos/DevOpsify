import { Typography ,Box} from '@mui/material';
import React from 'react';


const Home = () => (
  <Box sx={{my:10}}>
    <Typography variant="h5"
            noWrap
            component="div"
            sx={{ my:4}}
            fontWeight="550">Github Contribution Chart</Typography>
    <img src="http://ghchart.rshah.org/Oubaydos" alt="Oubaydos's Github chart"/>
  </Box>
);

export default Home;
