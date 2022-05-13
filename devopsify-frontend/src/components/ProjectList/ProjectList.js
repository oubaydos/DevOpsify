import * as React from "react";
import {Paper,Box,Typography} from "@mui/material";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import config from "../../config.json";
import StarIcon from '@mui/icons-material/Star';
import StarOutlineIcon from '@mui/icons-material/StarOutline';
import { Icon } from '@mui/material';


const drawerWidth = config.DRAWER_WIDTH + 20 + "px";

const columns = [
  { id: "fav", label: "Favorites", minWidth: 10 },
  { id: "name", label: "Name", minWidth: 100 },
  { id: "status", label: "Status", minWidth: 100 },
  { id: "created-by", label: "Created By", minWidth: 100 },
];

function createData(fav, name, status, createdBy) {
  return { fav, name, status, "created-by":createdBy };
}

const rows = [
  createData(true,"hello-world", "empty", "hamza"),
  createData(false,"maven-app", "in progress", "hamza"),
  createData(true,"devopsify", "done", "hamza"),
];

const favIcon = <Icon ><StarIcon /></Icon>;

export default function ProjectList() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);


  const contentToShow=(column, value)=>{
    if(column.format && typeof value === "number")
      return column.format(value);
    if(column.id==='fav')
      return (<Icon color="secondary">{value?<StarIcon />:<StarOutlineIcon/>}<StarIcon /></Icon>);
    else 
      return value;
  }

  return (
    <Box>
          <Typography variant="h5"
            noWrap
            component="div"
            sx={{ my:4 }}
            fontWeight="550">Your projects</Typography>
          <Paper
      sx={{
        width: `calc(100%-${drawerWidth})`,
        overflow: "hidden",
        mx: drawerWidth,
      }}
    >
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.id === "fav" ? <Icon ><StarIcon /></Icon> : column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                    {columns.map((column) => {
                      const value = row[column.id];
                      return (
                        <TableCell key={column.id} align={column.align}>
                          {contentToShow(column,value)}
                        </TableCell>
                      );
                    })}
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
    </Box>
  );
}
