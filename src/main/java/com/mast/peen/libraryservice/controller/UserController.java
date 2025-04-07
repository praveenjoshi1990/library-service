package com.mast.peen.libraryservice.controller;


import com.mast.peen.libraryservice.domain.USER_ROLE;
import com.mast.peen.libraryservice.domain.User;
import com.mast.peen.libraryservice.helpers.ApiResponseMapper;
import com.mast.peen.libraryservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Api(tags = "User Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final ApiResponseMapper apiResponseMapper;

  //Create a user feature
  @ApiOperation(value = "Create/register a user")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PostMapping("/user")
  public ResponseEntity registerUser(@RequestParam String username, @RequestParam String password,
      @RequestParam USER_ROLE role, HttpServletRequest request) {
    User user = userService.createUser(username, password, role);
    URI newResource = UriComponentsBuilder.newInstance()
        .path(request.getAttribute(
            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) +
            "/" + user.getId()).build().toUri();
    return apiResponseMapper.mapServiceCreateResponse(newResource, user);
  }

  @ApiOperation(value = "User login request")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestParam String username, @RequestParam String password) {
    userService.authenticateUser(username, password);
    return apiResponseMapper.mapServiceOkResponse("Login successful");
  }

  @ApiOperation(value = "Get a user")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @GetMapping("/{userId}")
  public ResponseEntity getUserById(@PathVariable Long userId) {
    User user = userService.getUserById(userId);
    return apiResponseMapper.mapServiceOkResponse(user);
  }
}
