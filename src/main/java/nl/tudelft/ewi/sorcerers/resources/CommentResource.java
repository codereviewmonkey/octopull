package nl.tudelft.ewi.sorcerers.resources;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.tudelft.ewi.sorcerers.usecases.CreateCommentFromWarning;

@RolesAllowed("user")
@Path("/comment")
public class CommentResource {
	private CreateCommentFromWarning ccfw;
	
	@Inject
	public CommentResource(CreateCommentFromWarning ccfw) {
		this.ccfw = ccfw;
	}

	@POST
	@Path("from-warning")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createCommentFromWarning(
			@FormParam("repo") String repo,
			@FormParam("commit") String commit,
			@FormParam("pullRequest") Integer pullRequest,
			@FormParam("warningId") Integer warningId,
			@FormParam("position") Integer position) throws IOException {
		this.ccfw.execute(repo, commit, pullRequest, warningId, position);
		return Response.ok().build();
	}
}
