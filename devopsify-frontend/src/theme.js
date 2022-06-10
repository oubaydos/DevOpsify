import { createTheme, experimental_sx as sx } from "@mui/material/styles";
import colors from "./utils/colors.json";


const theme = createTheme({
  typography: {
    fontFamily: ["Inter"].join(","),
    fontSize: 15,
    button: {
      textTransform: "none",
    },
  },
  palette: {
    primary: {
      main: colors.PRIMARY,
    },
    secondary: {
      main: colors.SECONDARY,
    },
    grey: {
      main: colors.GREY,
    },
  },
  components: {
    MuiAvatar: {
      styleOverrides: {
        root: sx({
          bgcolor: colors.SECONDARY,
        }),
      },
    },
  },
});

export default theme;
