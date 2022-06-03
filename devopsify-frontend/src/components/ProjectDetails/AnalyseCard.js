import { Box, Container, Typography } from "@mui/material";
import { ArrowForwardIos } from "@mui/icons-material";
import * as React from "react";

const styles = {
  card: {
    width: "800px ",
    height: "200px",
    background: "rgba(255, 255, 255, 0.8)",
    boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
    borderRadius: "10px",
    position: "relative"
  },
  title: {
    fontStyle: "normal",
    fontSize: "16px",
    lineWeight: "150%",
  },
  status: {
    good: {
      backgroundColor: "green",
    },
    bad: {
      backgroundColor: "red",
    },
    passed: {
      backgroundColor: "yellow",
    },
  },
};

const AnalyseCard = ({ analyseResults, children, logo, name }) => {
  const getStatus = (status) => (
    <Typography
      className="status"
      variant="p"
      sx={{
        fontSize: "16px",
        px: 2,
        py: 1,
        borderRadius: 10,
        color: "white",
        position: "absolute",
        right: 30,
        bottom: 15,
      }}
      style={styles.status[status]}
    >
      {status}
    </Typography>
  );

  return (
    <Container style={styles.card}>
      <Box
        className="card-header"
        sx={{
          display: "flex",
          flexDirection: "row",
          p: 1,
          position: "relative",
        }}
      >
        <img
          src={logo}
          alt="logo"
          height="50px"
          style={{ marginRight: 20 }}
        />
        <Typography style={styles.title} sx={{ mt: "14px" }} fontWeight="bold">
          {name}
        </Typography>
        <Box
          ClassName="more-details"
          sx={{
            display: "flex",
            flexDirection: "row",
            p: 1,
            position: "absolute",
            right: 0,
            top: 0,
          }}
        >
          <Typography fontSize="14px" sx={{ mt: "14px", mx: 1 }}>
            more details
          </Typography>
          <ArrowForwardIos fontSize="14px" sx={{ mt: "16px" }} />
        </Box>
      </Box>
      <Container className="card-body">{children}</Container>
      {getStatus(analyseResults.status)}
    </Container>
  );
};

export default AnalyseCard;
